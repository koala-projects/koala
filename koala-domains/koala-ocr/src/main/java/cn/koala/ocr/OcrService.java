package cn.koala.ocr;

import ai.djl.modality.cv.output.DetectedObjects;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * OCR服务接口
 *
 * @author Houtaroy
 */
public interface OcrService {

  List<DetectedObjects.DetectedObject> image(MultipartFile file);

  List<String> pdf(MultipartFile file);
}
