package cn.koala.ocr.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * OCR属性
 *
 * @author Houtaroy
 */
@ConfigurationProperties("koala.ocr")
@Data
public class OcrProperties {

  public static final String DEFAULT_MODEL_PATH = "/tmp/koala-ocr/models/";

  public static final String DEFAULT_DETECT_MODEL_NAME = "ch_PP-OCRv3_det_infer_onnx.zip";

  public static final String DEFAULT_RECOGNIZE_MODEL_NAME = "ch_PP-OCRv3_rec_infer_onnx.zip";

  private String modelPath = DEFAULT_MODEL_PATH;

  private String detectModelName = DEFAULT_DETECT_MODEL_NAME;

  private String recognizeModelName = DEFAULT_RECOGNIZE_MODEL_NAME;
}
