package cn.koala.code.processors.support.mybatis;

import cn.koala.code.processors.AbstractContextProcessor;
import cn.koala.code.processors.support.TableHelper;
import cn.koala.database.DatabaseTable;
import cn.koala.database.DatabaseTableColumn;
import com.google.common.base.CaseFormat;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * MyBatis上下文加工器
 *
 * @author Houtaroy
 */
public class MyBatisProcessor extends AbstractContextProcessor<DatabaseTable> {
  public MyBatisProcessor() {
    super(DatabaseTable.class);
  }

  @Override
  protected Map<String, Object> doProcess(DatabaseTable context) {
    return Map.of("mybatis", processMyBatisContext(context));
  }

  protected MyBatisContext processMyBatisContext(DatabaseTable context) {
    return MyBatisContext.builder()
      .columns(processMyBatisColumns(context))
      .stateful(TableHelper.isStateful(context))
      .auditable(TableHelper.isAuditable(context))
      .build();
  }

  protected List<MyBatisColumn> processMyBatisColumns(DatabaseTable context) {
    return context.getColumns().stream().map(this::processMyBatisColumn).collect(Collectors.toList());
  }

  protected MyBatisColumn processMyBatisColumn(DatabaseTableColumn column) {
    return MyBatisColumn.builder()
      .columnName(column.getName())
      .propertyName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, column.getName()))
      .build();
  }
}
