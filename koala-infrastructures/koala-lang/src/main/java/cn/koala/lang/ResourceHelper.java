package cn.koala.lang;

import java.io.File;
import java.net.URL;
import java.util.Optional;

/**
 * @author Houtaroy
 */
public abstract class ResourceHelper {
  /**
   * 获取文件
   *
   * @param filename 文件名
   * @return 文件
   */
  public static Optional<File> getFile(String filename) {
    return Optional.ofNullable(Thread.currentThread().getContextClassLoader().getResource(filename))
      .map(URL::getFile)
      .map(File::new);
  }
}
