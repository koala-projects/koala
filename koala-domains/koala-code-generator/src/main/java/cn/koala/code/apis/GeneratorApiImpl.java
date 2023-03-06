package cn.koala.code.apis;

import cn.koala.code.CodeGeneratorProperties;
import cn.koala.code.DatabaseGenerator;
import cn.koala.code.DatabaseMultiGenerator;
import cn.koala.code.database.DatabaseHelper;
import cn.koala.code.database.Table;
import cn.koala.code.processors.ContextProcessor;
import cn.koala.code.processors.DelegatingContextProcessor;
import cn.koala.code.processors.DomainProcessor;
import cn.koala.code.processors.StaticProcessor;
import cn.koala.code.processors.TableProcessor;
import cn.koala.web.DataResponse;
import com.jfinal.template.Engine;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器接口实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class GeneratorApiImpl implements GeneratorApi {
  private final Engine engine;
  private final CodeGeneratorProperties properties;
  private final DataSource dataSource;

  @Override
  public DataResponse<List<Table>> tables() throws SQLException {
    return DataResponse.ok(DatabaseHelper.getTables(dataSource.getConnection().getMetaData(), null, null, null));
  }

  @Override
  public DataResponse<Map<String, String>> preview(String table) {
    return null;
  }

  @Override
  public void test() throws Exception {
    DatabaseMultiGenerator multiGenerator = new DatabaseMultiGenerator(List.of(api()));
    System.out.println(multiGenerator.generate(DatabaseHelper.getTable(dataSource, null, null, "oauth2_registered_client", true)));
    System.out.println(multiGenerator.generate(DatabaseHelper.getTable(dataSource, null, null, "system_department", true)));
  }

  protected DatabaseGenerator api() {
    ContextProcessor processor = new DelegatingContextProcessor(List.of(new StaticProcessor("package", "cn.koala.test"), new TableProcessor(), new DomainProcessor("system_")));
    return new DatabaseGenerator(engine, "koala-system/Api.java", processor);
  }

  protected DatabaseGenerator apiImpl() {
    ContextProcessor processor = new DelegatingContextProcessor(List.of(new StaticProcessor("package", "cn.koala.test"), new TableProcessor(), new DomainProcessor("system_")));
    return new DatabaseGenerator(engine, "koala-system/ApiImpl.java", processor);
  }

  protected DatabaseGenerator service() {
    ContextProcessor processor = new DelegatingContextProcessor(List.of(new StaticProcessor("package", "cn.koala.test"), new TableProcessor(), new DomainProcessor("system_")));
    return new DatabaseGenerator(engine, "koala-system/Service.java", processor);
  }

  protected DatabaseGenerator entity() {
    ContextProcessor processor = new DelegatingContextProcessor(List.of(new StaticProcessor("package", "cn.koala.test"), new TableProcessor(), new DomainProcessor("system_")));
    return new DatabaseGenerator(engine, "koala-system/Entity.java", processor);
  }

  protected DatabaseGenerator repository() {
    ContextProcessor processor = new DelegatingContextProcessor(List.of(new StaticProcessor("package", "cn.koala.test"), new TableProcessor(), new DomainProcessor("system_")));
    return new DatabaseGenerator(engine, "koala-system/Repository.java", processor);
  }

  protected DatabaseGenerator mapper() {
    ContextProcessor processor = new DelegatingContextProcessor(List.of(new StaticProcessor("package", "cn.koala.test"), new TableProcessor(), new DomainProcessor("system_")));
    return new DatabaseGenerator(engine, "koala-system/Mapper.xml", processor);
  }
}
