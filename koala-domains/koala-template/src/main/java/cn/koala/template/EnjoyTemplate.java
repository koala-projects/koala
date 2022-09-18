package cn.koala.template;

import com.jfinal.template.EngineConfig;
import com.jfinal.template.source.ISource;
import lombok.RequiredArgsConstructor;

/**
 * Enjoy模板
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class EnjoyTemplate implements ISource {

  protected final Template template;

  @Override
  public boolean isModified() {
    return false;
  }

  @Override
  public String getCacheKey() {
    return null;
  }

  public StringBuilder getContent() {
    return new StringBuilder(template.getContent());
  }

  @Override
  public String getEncoding() {
    return EngineConfig.DEFAULT_ENCODING;
  }
}
