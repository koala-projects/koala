package cn.koala.lang.media;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

/**
 * @author Houtaroy
 */
public abstract class ImageHelper {
  private static final double DEFAULT_QUALITY = 0.4;
  private static final long MB = 1024L * 1024;
  private static final long[] SCALE_INTERVALS = new long[]{MB, 2 * MB, 10 * MB, 30 * MB};
  private static final double[] SCALE_VALUES = new double[]{1d, 0.7d, 0.5d, 0.4d};

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
