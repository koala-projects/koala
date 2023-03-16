package cn.koala.code.services;

import cn.koala.code.Code;
import cn.koala.code.SimpleCode;
import cn.koala.code.processors.ContextProcessor;
import cn.koala.database.DatabaseTable;
import cn.koala.template.Template;
import cn.koala.template.services.TemplateService;
import com.jfinal.template.Engine;

import java.util.Map;

/**
 * 基于Enjoy模板引擎的代码服务类
 *
 * @author Houtaroy
 */
public class EnjoyCodeService extends BaseTemplateCodeService {
  private static final String ENGINE_NAME = "koala-code";
  protected Engine engine;

  public EnjoyCodeService(TemplateService templateService, ContextProcessor processor) {
    super(templateService, processor);
    init();
  }

  protected void init() {
    Engine engine = Engine.use(ENGINE_NAME);
    this.engine = engine == null ? Engine.create(ENGINE_NAME) : engine;
  }

  @Override
  protected Code doGenerate(DatabaseTable table, Template template, Map<String, Object> context) {
    return new SimpleCode(template.getName(), engine.getTemplateByString(template.getContent()).renderToString(context));
  }
}
