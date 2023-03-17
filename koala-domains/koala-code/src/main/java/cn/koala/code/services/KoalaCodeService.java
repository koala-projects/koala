package cn.koala.code.services;

import cn.koala.code.Code;
import cn.koala.code.SimpleCode;
import cn.koala.code.processors.ContextProcessor;
import cn.koala.template.Template;
import com.jfinal.template.Engine;
import org.apache.commons.io.FilenameUtils;
import org.springframework.lang.NonNull;

import java.util.Map;

/**
 * 基于Enjoy模板引擎的考拉代码服务类
 *
 * @author Houtaroy
 */
public class KoalaCodeService extends BaseTemplateCodeService {
  private static final String ENGINE_NAME = "koala-code";
  protected Engine engine;

  public KoalaCodeService(ContextProcessor processor, String downloadPath) {
    super(processor, downloadPath);
    init();
  }

  protected void init() {
    Engine engine = Engine.use(ENGINE_NAME);
    this.engine = engine == null ? Engine.create(ENGINE_NAME) : engine;
  }

  @Override
  protected Code generate(@NonNull Template template, Map<String, Object> context) {
    return new SimpleCode(
      FilenameUtils.getPath(template.getName()) + context.get("name") + FilenameUtils.getName(template.getName()),
      engine.getTemplateByString(template.getContent()).renderToString(context)
    );
  }
}
