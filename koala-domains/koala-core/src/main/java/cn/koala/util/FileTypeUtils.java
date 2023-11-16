package cn.koala.util;

import org.apache.tika.Tika;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件类型工具类
 *
 * @author Houtaroy
 */
public abstract class FileTypeUtils {

  private static final Tika INSTANCE = new Tika();

  public static boolean isImage(File file) throws IOException {
    return isImage(new FileInputStream(file));
  }

  public static boolean isPdf(File file) throws IOException {
    return isPdf(new FileInputStream(file));
  }

  public static boolean isImage(InputStream inputStream) throws IOException {
    return isType(inputStream, "image");
  }

  public static boolean isPdf(InputStream inputStream) throws IOException {
    return isType(inputStream, "application/pdf");
  }

  public static boolean isType(File file, String typePrefix) throws IOException {
    return isType(new FileInputStream(file), typePrefix);
  }

  public static boolean isType(InputStream inputStream, String typePrefix) throws IOException {
    return INSTANCE.detect(inputStream).startsWith(typePrefix);
  }
}
