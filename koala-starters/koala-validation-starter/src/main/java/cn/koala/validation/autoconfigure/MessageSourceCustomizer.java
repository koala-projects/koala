package cn.koala.validation.autoconfigure;

import cn.koala.validation.MessageSourceLocator;
import jakarta.validation.Configuration;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.validation.ValidationConfigurationCustomizer;
import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * 校验消息源定制器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class MessageSourceCustomizer implements ValidationConfigurationCustomizer {

  protected final MessageSource parent;
  protected final List<MessageSourceLocator> locators;

  @Override
  public void customize(Configuration<?> configuration) {
    configuration.messageInterpolator(new MessageInterpolatorFactory(getMessageSource()).getObject());
  }

  protected MessageSource getMessageSource() {
    ResourceBundleMessageSource result = new ResourceBundleMessageSource();
    result.setParentMessageSource(parent);
    result.setBasenames(locators.stream()
      .flatMap(locator -> Arrays.stream(locator.getBasenames()))
      .toArray(String[]::new));
    result.setDefaultEncoding(StandardCharsets.UTF_8.name());
    return result;
  }
}
