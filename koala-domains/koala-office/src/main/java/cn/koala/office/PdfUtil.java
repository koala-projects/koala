package cn.koala.office;

import cn.koala.utils.TextUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
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
  public static final int DEFAULT_ENCRYPTION_KEY_LENGTH = 128;

  private PdfUtil() {
  }

  /**
   * 读取pdf文本内容
   *
   * @param filePathName 文件路径名
   * @param password     文件密码
   * @param chomp        是否去除换行符
   * @return pdf文本内容
   * @throws IOException IO异常
   */
  public static String read(String filePathName, String password, boolean chomp) throws IOException {
    return read(new File(filePathName), password, chomp);
  }

  /**
   * 读取pdf文本内容
   *
   * @param file     文件对象
   * @param password 文件密码
   * @param chomp    是否去除换行符
   * @return pdf文本内容
   * @throws IOException IO异常
   */
  public static String read(File file, String password, boolean chomp) throws IOException {
    try (PDDocument pdf = PDDocument.load(file, password)) {
      String result = new PDFTextStripper().getText(pdf);
      return chomp ? TextUtil.chomp(result) : result;
    }
  }

  /**
   * 将pdf文件内容读取为图片列表
   *
   * @param filePathName 文件路径名
   * @param password     文件密码
   * @return 图片列表
   * @throws IOException IO异常
   */
  public static List<BufferedImage> images(String filePathName, String password) throws IOException {
    return images(new File(filePathName), password);
  }

  /**
   * 将pdf文件内容读取为图片列表
   *
   * @param file     文件对象
   * @param password 文件密码
   * @return 图片列表
   * @throws IOException IO异常
   */
  public static List<BufferedImage> images(File file, String password) throws IOException {
    try (PDDocument pdf = PDDocument.load(file, password)) {
      PDFRenderer renderer = new PDFRenderer(pdf);
      List<BufferedImage> result = new ArrayList<>(pdf.getNumberOfPages());
      for (int i = 0; i < pdf.getNumberOfPages(); i++) {
        result.add(renderer.renderImage(i));
      }
      return result;
    }
  }

  /**
   * 将PDF文件中的每一页另存为图片
   * 例如: test/test.pdf有两页, 则生成: test/images/test-1.png, test/images/test-2.png
   *
   * @param filePathName    文件路径名
   * @param password        密码
   * @param destinationPath 目标路径
   * @throws IOException IO异常
   */
  public static void saveAsImages(String filePathName, String password, String destinationPath) throws IOException {
    FileUtils.forceMkdir(new File(destinationPath));
    String imageNameTemplate = destinationPath + "/" + FilenameUtils.getName(filePathName) + "-%d.png";
    List<BufferedImage> images = images(filePathName, password);
    for (int i = 0; i < images.size(); i++) {
      ImageIO.write(
        images.get(i),
        "png",
        new File(String.format(imageNameTemplate, i + 1))
      );
    }
  }

  /**
   * 将PDF文件加密
   *
   * @param filePathName 文件路径名
   * @param password     密码
   * @param destination  目标文件名
   * @throws IOException IO异常
   */
  public static void encrypt(String filePathName, String password, String destination) throws IOException {
    try (PDDocument pdf = PDDocument.load(new File(filePathName))) {
      StandardProtectionPolicy spp = new StandardProtectionPolicy(password, password, new AccessPermission());
      spp.setEncryptionKeyLength(DEFAULT_ENCRYPTION_KEY_LENGTH);
      pdf.protect(spp);
      pdf.save(destination);
    }
  }

  /**
   * 将PDF文件解密
   *
   * @param filePathName 文件路径名
   * @param password     密码
   * @param destination  目标文件名
   * @throws IOException IO异常
   */
  public static void decrypt(String filePathName, String password, String destination) throws IOException {
    try (PDDocument pdf = PDDocument.load(new File(filePathName, password))) {
      pdf.setAllSecurityToBeRemoved(true);
      pdf.save(destination);
    }
  }
}
