package cn.houtaroy.koala.component.druid;

import cn.houtaroy.koala.component.constant.MapConstant;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLAllColumnExpr;
import com.alibaba.druid.sql.ast.expr.SQLCharExpr;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.expr.SQLVariantRefExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 查询语句访问者
 * 只适用于select语句, 可以获取查询列名与查询参数
 * 查询参数书写格式为#{[a-zA-z]}
 *
 * @author Houtaroy
 */
@Data
@SuppressWarnings("PMD")
public class SelectASTVisitor implements SQLASTVisitor {
  public static final Pattern PARAMETER_PATTERN = Pattern.compile("#\\{[a-zA-z]*}");
  private static final int PARAMETER_START_INDEX = 2;

  protected List<SQLSelectItem> selectItems = new ArrayList<>();
  protected List<SelectTable> selectTables = new ArrayList<>();
  protected List<SelectColumn> selectColumns = new ArrayList<>();
  protected List<String> parameters = new ArrayList<>();

  private Map<Class<? extends SQLExpr>, BiConsumer<SQLExpr, String>> selectColumnExprFunctions;

  /**
   * 构造函数
   */
  public SelectASTVisitor() {
    selectColumnExprFunctions = new ConcurrentHashMap<>(MapConstant.DEFAULT_SIZE);
    selectColumnExprFunctions.put(SQLAllColumnExpr.class, this::computeAllColumn);
    selectColumnExprFunctions.put(SQLIdentifierExpr.class, this::computeIdentifier);
    selectColumnExprFunctions.put(SQLMethodInvokeExpr.class, this::computeMethodInvoke);
    selectColumnExprFunctions.put(SQLPropertyExpr.class, this::computeProperty);
  }

  @Override
  public void endVisit(SQLSelectQueryBlock x) {
    computeSelectColumns();
  }

  @Override
  public boolean visit(SQLExprTableSource x) {
    selectTables.add(new SelectTable(x));
    return false;
  }

  @Override
  public boolean visit(SQLCharExpr x) {
    computeParameter(x.toString());
    return false;
  }

  @Override
  public boolean visit(SQLSelectItem x) {
    selectItems.add(x);
    return false;
  }

  @Override
  public boolean visit(SQLVariantRefExpr x) {
    computeParameter(x.getName());
    return false;
  }

  /**
   * 访问查询参数表达式, 匹配查询参数
   *
   * @param expr 查询参数表达式
   */
  protected void computeParameter(String expr) {
    Matcher matcher = PARAMETER_PATTERN.matcher(expr);
    if (matcher.find()) {
      String match = matcher.group();
      parameters.add(match.substring(PARAMETER_START_INDEX, match.length() - 1));
    }
  }

  /**
   * 计算查询列
   */
  protected void computeSelectColumns() {
    selectItems.forEach(item ->
      Optional.ofNullable(selectColumnExprFunctions.get(item.getExpr().getClass()))
        .ifPresent(function -> function.accept(item.getExpr(), item.getAlias()))
    );
  }

  /**
   * 计算查询全部列表达式
   *
   * @param expr  查询全部列表达式
   * @param alias 别名
   */
  protected void computeAllColumn(SQLExpr expr, String alias) {
    selectColumns.add(new SelectColumn(selectTables.get(0).getName(), expr.toString(), alias));
  }

  /**
   * 计算确定列表达式
   *
   * @param expr  确定列表达式
   * @param alias 别名
   */
  protected void computeIdentifier(SQLExpr expr, String alias) {
    SQLIdentifierExpr identifier = (SQLIdentifierExpr) expr;
    selectColumns.add(new SelectColumn(selectTables.get(0).getName(), identifier.getName(), alias));
  }

  /**
   * 计算方法列表达式
   *
   * @param expr  方法列表达式
   * @param alias 别名
   */
  protected void computeMethodInvoke(SQLExpr expr, String alias) {
    selectColumns.add(new SelectColumn(null, expr.toString(), alias));
  }

  /**
   * 计算属性列表达式
   *
   * @param expr  属性列表达式
   * @param alias 别名
   */
  protected void computeProperty(SQLExpr expr, String alias) {
    SQLPropertyExpr property = (SQLPropertyExpr) expr;
    selectColumns.add(new SelectColumn(getSelectTableNameByAlias(property.getOwnerName()), property.getName(), alias));
  }

  /**
   * 根据查询表别名获取查询表
   *
   * @param alias 查询表别名
   * @return 查询表
   */
  protected Optional<SelectTable> getSelectTableByAlias(String alias) {
    return selectTables.stream().filter(table -> alias.equals(table.getAlias())).findFirst();
  }

  /**
   * 根据查询表别名获取查询表名
   * getSelectTableNameByAlias("t") -> "t_user" or null
   *
   * @param alias 查询表别名
   * @return 查询表名
   */
  protected String getSelectTableNameByAlias(String alias) {
    return getSelectTableByAlias(alias).map(SelectTable::getName).orElse(null);
  }
}
