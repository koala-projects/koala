package cn.koala.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * PDF工具类
 *
 * @author Houtaroy
 */
public final class PdfUtil {
  private PdfUtil() {
  }

  /**
   * 读取pdf文件
   *
   * @param filePathName 文件路径名
   * @return PDDocument对象
   * @throws IOException IO异常
   */
  public static PDDocument load(String filePathName) throws IOException {
    return PDDocument.load(new File(filePathName));
  }

  /**
   * 读取pdf文本内容
   *
   * @param filePathName 文件路径名
   * @return pdf文本内容
   * @throws IOException IO异常
   */
  public static String read(String filePathName) throws IOException {
    return read(filePathName, false);
  }

  /**
   * 读取pdf文本内容
   *
   * @param filePathName 文件路径名
   * @param chomp        是否去除换行符
   * @return pdf文本内容
   * @throws IOException IO异常
   */
  public static String read(String filePathName, boolean chomp) throws IOException {
    PDDocument document = load(filePathName);
    String result = new PDFTextStripper().getText(document);
    document.close();
    return chomp ? ArticleUtil.chomp(result) : result;
  }

  /**
   * 将pdf文件内容读取为图片列表
   *
   * @param filePathName 文件路径名
   * @return 图片列表
   * @throws IOException IO异常
   */
  public static List<BufferedImage> images(String filePathName) throws IOException {
    PDDocument document = load(filePathName);
    PDFRenderer renderer = new PDFRenderer(document);
    List<BufferedImage> result = new ArrayList<>(document.getNumberOfPages());
    for (int i = 0; i < document.getNumberOfPages(); i++) {
      result.add(renderer.renderImage(i));
    }
    document.close();
    return result;
  }

  /**
   * 将PDF文件中的每一页另存为图片
   * 详情查看{@link PdfUtil#saveAsImages(String, String) saveAsImages}
   *
   * @param filePathName 文件路径名
   * @throws IOException IO异常
   */
  public static void saveAsImages(String filePathName) throws IOException {
    saveAsImages(filePathName, String.format("%s/images", FilenameUtils.getFullPath(filePathName)));
  }

  /**
   * 将PDF文件中的每一页另存为图片
   * 例如: test/test.pdf有两页, 则生成: test/images/test-1.png, test/images/test-2.png
   *
   * @param filePathName    文件路径名
   * @param destinationPath 目标路径
   * @throws IOException IO异常
   */
  public static void saveAsImages(String filePathName, String destinationPath) throws IOException {
    PDDocument document = load(filePathName);
    PDFRenderer renderer = new PDFRenderer(document);
    FileUtils.forceMkdir(new File(destinationPath));
    String imageNameTemplate = destinationPath + "/" + FilenameUtils.getName(filePathName) + "-%d.png";
    for (int i = 0; i < document.getNumberOfPages(); i++) {
      ImageIO.write(
        renderer.renderImage(i),
        "png",
        new File(String.format(imageNameTemplate, i + 1))
      );
    }
  }
}
