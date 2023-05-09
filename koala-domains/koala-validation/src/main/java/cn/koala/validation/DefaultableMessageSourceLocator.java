package cn.koala.validation;

/**
 * 有默认值的消息源定位器
 * <p>
 * 例如: 消息源为test.properties, 会同时支持默认消息源test-default.properties
 *
 * @author Houtaroy
 */
public class DefaultableMessageSourceLocator extends SimpleMessageSourceLocator {

  private static final String DEFAULT_SUFFIX = "-default";

  protected final String defaultBasename;

  public DefaultableMessageSourceLocator(String basename) {
    super(basename);
    this.defaultBasename = basename + DEFAULT_SUFFIX;
  }

  @Override
  public String[] getBasenames() {
    return new String[]{basename, defaultBasename};
  }
}
