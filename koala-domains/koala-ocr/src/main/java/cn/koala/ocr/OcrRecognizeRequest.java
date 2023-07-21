package cn.koala.ocr;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * OCR识别请求
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
public class OcrRecognizeRequest {

  String type;

  String source;
}
