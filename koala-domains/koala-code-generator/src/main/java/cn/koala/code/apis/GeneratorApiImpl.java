package cn.koala.code.apis;

import cn.koala.code.ContextProcessor;
import cn.koala.code.DelegatingContextProcessor;
import cn.koala.code.StaticProcessor;
import cn.koala.code.enjoy.EngineFactory;
import cn.koala.code.table.TableContext;
import cn.koala.code.table.TableGenerator;
import cn.koala.code.table.TableMultiGenerator;
import cn.koala.code.table.processors.EntityProcessor;
import cn.koala.code.table.processors.TableProcessor;
import cn.koala.toolkit.jdbc.DatabaseHelper;
import cn.koala.toolkit.jdbc.Table;
import cn.koala.web.DataResponse;
import com.jfinal.template.Engine;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

/**
 * 代码生成器接口实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class GeneratorApiImpl implements GeneratorApi {
  private final DataSource dataSource;
  private final Engine engine = EngineFactory.create("D://Temp//koala//builder");

  @Override
  public DataResponse<List<Table>> tables() throws SQLException {
    return DataResponse.ok(DatabaseHelper.getTables(dataSource.getConnection().getMetaData(), null, null, null));
  }

  @Override
  public void test() throws Exception {
    TableMultiGenerator multiGenerator = new TableMultiGenerator(List.of(entity(), mapper()));
    System.out.println(multiGenerator.generate(context()));
  }

  protected TableContext context() throws SQLException {
    DatabaseMetaData meta = dataSource.getConnection().getMetaData();
    return TableContext.from(DatabaseHelper.getTables(meta, null, null, null).get(2), meta);
  }

  protected TableGenerator entity() {
    ContextProcessor processor = new DelegatingContextProcessor(List.of(new StaticProcessor("packageName", "cn.koala.test"), new TableProcessor(), new EntityProcessor()));
    return new TableGenerator(engine, "koala-system/Entity.java", processor);
  }

  protected TableGenerator mapper() {
    ContextProcessor processor = new DelegatingContextProcessor(List.of(new StaticProcessor("packageName", "cn.koala.test"), new TableProcessor(), new EntityProcessor()));
    return new TableGenerator(engine, "koala-system/Mapper.xml", processor);
  }
}
