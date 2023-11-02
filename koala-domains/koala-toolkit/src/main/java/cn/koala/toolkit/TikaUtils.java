package cn.koala.toolkit;

import org.apache.tika.Tika;

import java.io.IOException;
import java.io.InputStream;

/**
 * TODO: 修改类描述
 *
 * @author Houtaroy
 */
public abstract class TikaUtils {

  private static final Tika INSTANCE = new Tika();

  public static boolean isImage(InputStream inputStream) throws IOException {
    return isType(inputStream, "image");
  }

  public static boolean isPdf(InputStream inputStream) throws IOException {
    return isType(inputStream, "application/pdf");
  }

  public static boolean isType(InputStream inputStream, String typePrefix) throws IOException {
    return INSTANCE.detect(inputStream).startsWith(typePrefix);
  }
}
