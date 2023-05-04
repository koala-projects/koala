package cn.koala.code.apis;

import cn.koala.code.Code;
import cn.koala.code.services.CodeService;
import cn.koala.database.Database;
import cn.koala.database.DatabaseTable;
import cn.koala.database.services.DatabaseService;
import cn.koala.template.Template;
import cn.koala.template.TemplateGroup;
import cn.koala.template.services.TemplateGroupService;
import cn.koala.template.services.TemplateService;
import cn.koala.web.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
  private final TemplateService templateService;
  protected final CodeService codeService;

  @Override
  public DataResponse<Map<String, List<Code>>> preview(CodeRequest request) {
    return DataResponse.ok(codeService.preview(getTables(request), getTemplates(request)));
  }

  @Override
  public DataResponse<String> download(CodeRequest request) {
    return new DataResponse<>(
      HttpStatus.OK.value(),
      "请求成功",
      "/api/code/download/" + codeService.download(getTables(request), getTemplates(request))
    );
  }

  protected List<DatabaseTable> getTables(CodeRequest request) {
    Assert.notEmpty(request.getTables(), "数据库表不能为空");
    Database database = databaseService.read(request.getDatabaseId());
    Assert.notNull(database, "数据库不存在");
    return request.getTables().stream().map(table -> databaseService.getTable(database, table)).toList();
  }

  protected List<Template> getTemplates(CodeRequest request) {
    TemplateGroup templateGroup = templateGroupService.read(request.getTemplateGroupId());
    Assert.notNull(templateGroup, "代码模板不存在");
    return templateService.read(Map.of("groupId", templateGroup.getId()));
  }
}
