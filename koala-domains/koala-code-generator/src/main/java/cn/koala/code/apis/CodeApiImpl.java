package cn.koala.code.apis;

import cn.koala.code.CodeService;
import cn.koala.code.template.Template;
import cn.koala.code.template.TemplateGroupService;
import cn.koala.database.Table;
import cn.koala.database.services.TableService;
import cn.koala.web.DataResponse;
import lombok.RequiredArgsConstructor;
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
  protected final TableService tableService;
  protected final TemplateGroupService templateGroupService;
  protected final CodeService codeService;

  @Override
  public DataResponse<Map<String, String>> preview(CodePreviewRequest request) {
    Table table = tableService.load(request.getTable());
    Assert.notNull(table, "数据库表不存在");
    List<Template> templates = templateGroupService.load(request.getGroup()).getTemplates();
    Assert.notEmpty(templates, "未找到模板文件");
    return DataResponse.ok(codeService.generate(templates, table));
  }
}
