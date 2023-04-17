package cn.koala.validation.autoconfigure;

import cn.koala.validation.MessageSourceLocator;
import jakarta.validation.Configuration;
import org.springframework.boot.autoconfigure.validation.ValidationConfigurationCustomizer;
import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * 校验消息源定制器
 *
 * @author Houtaroy
 */
public class ValidationMessageSourceCustomizer implements ValidationConfigurationCustomizer {

  protected final String[] basenames;

  public ValidationMessageSourceCustomizer(String...basenames) {
    this.basenames = basenames;
  }

  public ValidationMessageSourceCustomizer(List<MessageSourceLocator> locators) {
    this(locators.stream()
      .flatMap(locator -> Arrays.stream(locator.getBasenames()))
      .toArray(String[]::new));
  }

  @Override
  public void customize(Configuration<?> configuration) {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasenames(basenames);
    messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
    configuration.messageInterpolator(new MessageInterpolatorFactory(messageSource).getObject());
  }
}
