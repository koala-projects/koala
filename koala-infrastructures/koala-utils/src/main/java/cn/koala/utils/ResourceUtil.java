package cn.koala.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Houtaroy
 */
public final class ResourceUtil {
  private ResourceUtil() {
  }

  /**
   * 读取资源文件内容
   *
   * @param filename 资源文件名
   * @return 内容流
   */
  public static Optional<Stream<String>> readLines(String filename) {
    return Optional.ofNullable(ResourceUtil.class.getClassLoader().getResourceAsStream(filename))
      .map(InputStreamReader::new)
      .map(BufferedReader::new)
      .map(BufferedReader::lines);
  }
}
