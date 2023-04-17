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


  public SimpleMessageSourceLocator(String basename) {
    this.basename = basename;
  }

  @Override
  public String[] getBasenames() {
    return new String[]{basename};
  }
}
