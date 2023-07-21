package cn.koala.ocr.autoconfigure;

import ai.djl.modality.cv.output.Rectangle;
import cn.koala.ocr.OcrApi;
import cn.koala.ocr.OcrProcessor;
import cn.koala.ocr.OcrService;
import cn.koala.ocr.jackson.RectangleMixin;
import cn.koala.ocr.support.DefaultOcrApi;
import cn.koala.ocr.support.DefaultOcrProcessor;
import cn.koala.ocr.support.DefaultOcrService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OCR自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@EnableConfigurationProperties(OcrProperties.class)
public class OcrAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public OcrProcessor ocrProcessor(OcrProperties properties) throws Exception {
    return new DefaultOcrProcessor(
      properties.getModelPath() + properties.getDetectModelName(),
      properties.getModelPath() + properties.getRecognizeModelName()
    );
  }

  @Bean
  @ConditionalOnMissingBean
  public OcrService ocrService(OcrProcessor processor) {
    return new DefaultOcrService(processor);
  }

  @Bean
  @ConditionalOnMissingBean
  public OcrApi ocrApi(OcrService service) {
    return new DefaultOcrApi(service);
  }

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer rectangleMixinCustomizer() {
    return builder -> builder.mixIn(Rectangle.class, RectangleMixin.class);
  }
}
