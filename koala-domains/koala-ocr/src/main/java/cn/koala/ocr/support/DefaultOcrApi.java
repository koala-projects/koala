package cn.koala.ocr.support;

import ai.djl.modality.cv.output.DetectedObjects;
import cn.koala.ocr.OcrApi;
import cn.koala.ocr.OcrService;
import cn.koala.web.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 默认OCR接口实现类
 *
 * @author Houtaroy
 */
@RestController
@RequiredArgsConstructor
public class DefaultOcrApi implements OcrApi {

  private final OcrService service;

  @Override
  public DataResponse<List<DetectedObjects.DetectedObject>> image(MultipartFile image) {
    return DataResponse.ok(this.service.image(image));
  }

  @Override
  public DataResponse<List<String>> pdf(MultipartFile pdf) {
    return DataResponse.ok(this.service.pdf(pdf));
  }
}
