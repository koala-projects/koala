package cn.koala.code.apis;

import cn.koala.code.Code;
import cn.koala.code.services.CodeService;
import cn.koala.database.Database;
import cn.koala.database.services.DatabaseService;
import cn.koala.template.TemplateGroup;
import cn.koala.template.services.TemplateGroupService;
import cn.koala.web.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 代码接口实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class CodeApiImpl implements CodeApi {
  protected final DatabaseService databaseService;
  protected final TemplateGroupService templateGroupService;
  protected final CodeService codeService;

  @Override
  public DataResponse<Map<String, List<Code>>> preview(CodeRequest request) {
    Assert.notEmpty(request.getTables(), "数据库表不能为空");
    Database database = databaseService.load(request.getDatabaseId());
    Assert.notNull(database, "数据库不存在");
    TemplateGroup templateGroup = templateGroupService.load(request.getTemplateGroupId());
    Assert.notNull(database, "代码模板不存在");
    return DataResponse.ok(request.getTables().stream().collect(Collectors.toMap(
      (table) -> table,
      (table) -> codeService.generate(databaseService.getTable(database, table), templateGroup)
    )));
  }
}
