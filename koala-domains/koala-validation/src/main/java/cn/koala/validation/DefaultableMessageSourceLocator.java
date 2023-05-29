package cn.koala.validation;

import java.util.Arrays;

/**
 * 有默认值的消息源定位器
 * <p>
 * 例如: 消息源为test.properties, 会同时支持默认消息源test-default.properties
 *
 * @author Houtaroy
 */
public record DefaultableMessageSourceLocator(String... basenames) implements MessageSourceLocator {

  private static final String DEFAULT_PREFIX = "validation.";
  private static final String DEFAULT_SUFFIX = "-default";

  public DefaultableMessageSourceLocator(String... basenames) {
    this.basenames = new String[basenames.length * 2];
    int current = 0;
    for (String basename : basenames) {
      String filename = DEFAULT_PREFIX + basename;
      this.basenames[current] = filename;
      current += 1;
      this.basenames[current] = filename + DEFAULT_SUFFIX;
      current += 1;
    }
  }

  @Override
  public String[] basenames() {
    return Arrays.copyOf(this.basenames, this.basenames.length);
  }
}
