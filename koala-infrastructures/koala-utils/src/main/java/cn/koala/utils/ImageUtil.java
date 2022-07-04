package cn.koala.utils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Objects;

/**
 * @author Houtaroy
 */
public final class ImageUtil {
  private static final String BASE64_PREFIX_TEMPLATE = "data:image/%s;base64,%s";
  private static final String BASE64_SPLIT = ",";
  private static final double DEFAULT_QUALITY = 0.4;
  private static final long MB = 1024L * 1024;
  private static final long[] SCALE_INTERVALS = new long[]{MB, 2 * MB, 10 * MB, 30 * MB};
  private static final double[] SCALE_VALUES = new double[]{1d, 0.7d, 0.5d, 0.4d};


  private ImageUtil() {
  }

  /**
   * 转换为base64
   *
   * @param filename 文件名
   * @return base64
   * @throws IOException IOException
   */
  public static String base64(String filename) throws IOException {
    return base64(filename, false);
  }

  /**
   * 转换为base64
   *
   * @param filename  文件名
   * @param hasPrefix 是否包含前缀
   * @return base64
   * @throws IOException IOException
   */
  public static String base64(String filename, boolean hasPrefix) throws IOException {
    return base64(new File(filename), hasPrefix);
  }

  /**
   * 转换为base64
   *
   * @param file 文件
   * @return base64
   * @throws IOException IOException
   */
  public static String base64(File file) throws IOException {
    return base64(file, false);
  }

  /**
   * 转换为base64
   *
   * @param file      文件
   * @param hasPrefix 是否包含前缀
   * @return base64
   * @throws IOException IOException
   */
  public static String base64(File file, boolean hasPrefix) throws IOException {
    return base64(ImageIO.read(file), FilenameUtils.getExtension(file.getName()), hasPrefix);
  }

  /**
   * 转换为base64
   *
   * @param image      图片缓存
   * @param formatName 图片格式
   * @param hasPrefix  是否包含前缀
   * @return base64
   * @throws IOException IOException
   */
  public static String base64(BufferedImage image, String formatName, boolean hasPrefix) throws IOException {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    ImageIO.write(image, formatName, stream);
    String base64 = Base64.getEncoder().encodeToString(stream.toByteArray());
    return hasPrefix ? String.format(BASE64_PREFIX_TEMPLATE, formatName, base64) : base64;
  }

  /**
   * 将base64保存为图片
   *
   * @param base64   base64
   * @param filename 图片文件名
   * @throws IOException IOException
   */
  public static void saveBase64(String base64, String filename) throws IOException {
    File file = new File(filename);
    FileUtils.forceMkdirParent(file);
    try (BufferedOutputStream stream = new BufferedOutputStream(Files.newOutputStream(file.toPath()))) {
      stream.write(Base64.getDecoder().decode(base64.substring(base64.indexOf(BASE64_SPLIT) + 1)));
    }
  }

  /**
   * 压缩图片, 自动判断压缩比例
   *
   * @param source      原图片路径
   * @param destination 压缩后图片路径
   * @throws IOException IOException
   */
  public static void compress(String source, String destination) throws IOException {
    compress(source, destination, determineScale(source), DEFAULT_QUALITY, "jpg");
  }

  /**
   * 压缩图片
   *
   * @param source      原图片路径
   * @param destination 压缩后图片路径
   * @param scale       压缩比例
   * @param quality     压缩质量
   * @param format      输出格式
   * @throws IOException IOException
   */
  public static void compress(String source, String destination, double scale, double quality, String format)
    throws IOException {
    Thumbnails.of(source).scale(scale).outputQuality(quality).outputFormat(format).toFile(destination);
  }

  /**
   * 压缩指定文件夹下的所有图片
   *
   * @param sourcePath      原文件夹
   * @param destinationPath 目标文件夹
   * @param rename          重命名策略
   * @param scale           压缩比例
   * @param quality         压缩质量
   * @param format          输出格式
   * @throws IOException IOException
   */
  public static void compress(String sourcePath, String destinationPath, Rename rename, double scale, double quality,
                              String format) throws IOException {
    Thumbnails.of(Objects.requireNonNull(new File(sourcePath).listFiles())).scale(scale).outputQuality(quality)
      .outputFormat(format).toFiles(new File(destinationPath), rename);
  }

  /**
   * 决定图片压缩比例
   *
   * @param filePathName 图片文件路径
   * @return 压缩比例
   */
  private static double determineScale(String filePathName) {
    long size = new File(filePathName).length();
    int index = 0;
    for (int i = 0; i < SCALE_INTERVALS.length; i++) {
      if (size < SCALE_INTERVALS[i]) {
        index = i;
        break;
      }
    }
    return SCALE_VALUES[index];
  }
}
