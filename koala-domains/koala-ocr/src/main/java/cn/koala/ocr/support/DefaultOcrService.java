package cn.koala.ocr.support;

import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.output.DetectedObjects;
import ai.djl.opencv.OpenCVImageFactory;
import cn.koala.ocr.OcrProcessor;
import cn.koala.ocr.OcrService;
import cn.koala.toolkit.MultipartFileHelper;
import cn.koala.web.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 默认OCR服务实现类
 *
 * @author Houtaroy
 */
@Slf4j
public class DefaultOcrService implements OcrService {

  private final OcrProcessor processor;

  public DefaultOcrService(OcrProcessor processor) {
    this.processor = processor;
  }

  @Override
  public List<DetectedObjects.DetectedObject> image(MultipartFile file) {
    try {
      Assert.isTrue(MultipartFileHelper.isImage(file), "待识别文件不是图片");
      return processor.process(OpenCVImageFactory.getInstance().fromInputStream(file.getInputStream())).items();
    } catch (IOException e) {
      LOGGER.error("上传文件[name={}]读取失败", file.getOriginalFilename(), e);
      throw new BusinessException("上传文件读取失败");
    } catch (Exception e) {
      LOGGER.error("图片识别失败", e);
      throw new BusinessException("图片识别失败");
    }
  }

  @Override
  public List<String> pdf(MultipartFile file) {
    try {
      Assert.isTrue(MultipartFileHelper.isPdf(file), "待识别文件不是PDF");
      try (PDDocument document = PDDocument.load(file.getInputStream())) {
        PDFRenderer renderer = new PDFRenderer(document);
        List<String> result = new ArrayList<>(document.getNumberOfPages());
        for (int page = 0; page < document.getNumberOfPages(); page++) {
          result.add(
            Optional.of(obtainPdfText(document, page + 1))
              .filter(StringUtils::hasText)
              .orElse(obtainPdfImage(renderer, page))
          );
        }
        return result;
      }
    } catch (IOException e) {
      LOGGER.error("上传文件[name={}]读取失败", file.getOriginalFilename(), e);
      throw new BusinessException("上传文件读取失败");
    } catch (Exception e) {
      LOGGER.error("PDF识别失败", e);
      throw new BusinessException("PDF识别失败");
    }
  }

  protected String obtainPdfText(PDDocument document, int page) throws IOException {
    PDFTextStripper textStripper = new PDFTextStripper();
    textStripper.setStartPage(page);
    textStripper.setEndPage(page);
    return textStripper.getText(document);
  }

  protected String obtainPdfImage(PDFRenderer renderer, int page) throws Exception {
    BufferedImage bufferedImage = renderer.renderImage(page);
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, "jpg", os);
    os.flush();
    Image image = OpenCVImageFactory.getInstance().fromInputStream(new ByteArrayInputStream(os.toByteArray()));
    return String.join("", this.processor.process(image).getClassNames());
  }
}
