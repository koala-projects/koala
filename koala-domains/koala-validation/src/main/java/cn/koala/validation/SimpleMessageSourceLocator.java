package cn.koala.validation;

import lombok.Getter;

/**
 * 简易校验消息源定位器
 *
 * @author Houtaroy
 */
@Getter
public class SimpleMessageSourceLocator implements MessageSourceLocator {

  protected String basename;
  protected String defaultBasename;

  public SimpleMessageSourceLocator(String basename) {
    this.basename = basename;
    this.defaultBasename = basename + "-default";
  }

  @Override
  public String[] getBasenames() {
    return new String[]{basename, defaultBasename};
  }
}
