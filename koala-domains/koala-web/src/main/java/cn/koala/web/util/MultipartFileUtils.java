package cn.koala.web.util;

import cn.koala.toolkit.TikaUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * MultipartFile工具类
 *
 * @author Houtaroy
 */
@Deprecated
public abstract class MultipartFileUtils {

  public static boolean isImage(MultipartFile multipartFile) throws IOException {
    return TikaUtils.isImage(multipartFile.getInputStream());
  }

  public static boolean isPdf(MultipartFile multipartFile) throws IOException {
    return TikaUtils.isPdf(multipartFile.getInputStream());
  }
}
