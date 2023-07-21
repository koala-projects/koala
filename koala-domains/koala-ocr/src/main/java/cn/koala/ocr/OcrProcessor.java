package cn.koala.ocr;

import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.output.DetectedObjects;

/**
 * OCR处理器接口
 *
 * @author Houtaroy
 */
public interface OcrProcessor {

  DetectedObjects process(Image image) throws Exception;
}
