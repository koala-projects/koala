package cn.koala.validation;

/**
 * 有默认值的消息源定位器
 *
 * @author Houtaroy
 */
public class DefaultableMessageSourceLocator extends SimpleMessageSourceLocator {

  protected String defaultBasename;

  public DefaultableMessageSourceLocator(String basename) {
    super(basename);
    this.defaultBasename = basename + "-default";
  }

  @Override
  public String[] getBasenames() {
    return new String[]{basename, defaultBasename};
  }
}
