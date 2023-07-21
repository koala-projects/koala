package cn.koala.ocr.support;

import ai.djl.inference.Predictor;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.output.BoundingBox;
import ai.djl.modality.cv.output.DetectedObjects;
import ai.djl.modality.cv.output.Rectangle;
import ai.djl.modality.cv.util.NDImageUtils;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDManager;
import ai.djl.opencv.OpenCVImageFactory;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelZoo;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import cn.koala.ocr.OcrProcessor;
import org.apache.commons.pool2.ObjectPool;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认OCR处理器
 *
 * @author Houtaroy
 */
public class DefaultOcrProcessor implements OcrProcessor {

  private final ObjectPool<Predictor<Image, DetectedObjects>> detectors;
  private final ObjectPool<Predictor<Image, String>> recognizers;

  public DefaultOcrProcessor(String detectModelPath, String recognizeModelPath) throws Exception {
    this(
      ModelZoo.loadModel(
        Criteria.builder()
          .optEngine("OnnxRuntime")
          .optModelName("inference")
          .setTypes(Image.class, DetectedObjects.class)
          .optModelPath(Paths.get(detectModelPath))
          .optProgress(new ProgressBar())
          .optTranslator(new PpWordDetectionTranslator(new ConcurrentHashMap<>()))
          .build()
      ),
      ModelZoo.loadModel(
        Criteria.builder()
          .optEngine("OnnxRuntime")
          .optModelName("inference")
          .setTypes(Image.class, String.class)
          .optModelPath(Paths.get(recognizeModelPath))
          .optProgress(new ProgressBar())
          .optTranslator(new PpWordRecognitionTranslator(new ConcurrentHashMap<>()))
          .build()
      )
    );
  }

  public DefaultOcrProcessor(ZooModel<Image, DetectedObjects> detectModel, ZooModel<Image, String> recognizeModel) {
    this.detectors = PredictorPoolFactory.create(detectModel);
    this.recognizers = PredictorPoolFactory.create(recognizeModel);
  }

  @Override
  public DetectedObjects process(Image image) throws Exception {

    Predictor<Image, DetectedObjects> detector = this.detectors.borrowObject();
    DetectedObjects detections = detector.predict(image);
    this.detectors.returnObject(detector);

    List<DetectedObjects.DetectedObject> boxes = detections.items();
    List<String> names = new ArrayList<>();
    List<Double> prob = new ArrayList<>();
    List<BoundingBox> rect = new ArrayList<>();

    Predictor<Image, String> recognizer = this.recognizers.borrowObject();
    for (DetectedObjects.DetectedObject box : boxes) {
      Image subImg = getSubImage(image, box.getBoundingBox());
      if (subImg.getHeight() * 1.0 / subImg.getWidth() > 1.5) {
        subImg = rotateImg(subImg);
      }
      String name = recognizer.predict(subImg);
      names.add(name);
      prob.add(-1.0);
      rect.add(box.getBoundingBox());
    }
    this.recognizers.returnObject(recognizer);

    return new DetectedObjects(names, prob, rect);
  }

  private Image getSubImage(Image img, BoundingBox box) {
    Rectangle rect = box.getBounds();
    double[] extended = extendRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    int width = img.getWidth();
    int height = img.getHeight();
    int[] recovered = {
      (int) (extended[0] * width),
      (int) (extended[1] * height),
      (int) (extended[2] * width),
      (int) (extended[3] * height)
    };
    return img.getSubImage(recovered[0], recovered[1], recovered[2], recovered[3]);
  }

  private double[] extendRect(double xMin, double yMin, double width, double height) {
    double centerX = xMin + width / 2;
    double centerY = yMin + height / 2;
    if (width > height) {
      width += height * 2.0;
      height *= 3.0;
    } else {
      height += width * 2.0;
      width *= 3.0;
    }
    double newX = centerX - width / 2 < 0 ? 0 : centerX - width / 2;
    double newY = centerY - height / 2 < 0 ? 0 : centerY - height / 2;
    double newWidth = newX + width > 1 ? 1 - newX : width;
    double newHeight = newY + height > 1 ? 1 - newY : height;
    return new double[]{newX, newY, newWidth, newHeight};
  }


  private Image rotateImg(Image image) {
    try (NDManager manager = NDManager.newBaseManager()) {
      NDArray rotated = NDImageUtils.rotate90(image.toNDArray(manager), 1);
      return OpenCVImageFactory.getInstance().fromNDArray(rotated);
    }
  }
}
