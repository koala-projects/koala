package cn.koala.ocr.jackson;

import ai.djl.modality.cv.output.Rectangle;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Rectangle Jackson Mixin
 *
 * @author Houtaroy
 */
public interface RectangleMixin {

  @JsonIgnore
  Rectangle getBounds();
}
