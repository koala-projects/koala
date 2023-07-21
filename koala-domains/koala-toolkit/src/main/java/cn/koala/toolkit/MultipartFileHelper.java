package cn.koala.toolkit;

import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * MultipartFile帮助类
 *
 * @author Houtaroy
 */
public abstract class MultipartFileHelper {

  private static final Tika tika = new Tika();

  public static boolean isImage(MultipartFile multipartFile) throws IOException {
    return tika.detect(multipartFile.getInputStream()).startsWith("image");
  }

  public static boolean isPdf(MultipartFile multipartFile) throws IOException {
    return tika.detect(multipartFile.getInputStream()).startsWith("application/pdf");
  }
}
