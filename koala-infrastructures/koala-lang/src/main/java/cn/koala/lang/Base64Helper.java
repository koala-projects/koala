package cn.koala.lang;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

/**
 * @author Houtaroy
 */
public abstract class Base64Helper {

  public static final String DESCRIPTION_TEMPLATE = "data:%s;base64,%s";
  public static final String DESCRIPTION_MARK = ";base64,";


  /**
   * 编码
   *
   * @param file 文件
   * @return base64字符串
   * @throws IOException IO异常
   */
  public static String encode(File file) throws IOException {
    return encode(file, false);
  }

  /**
   * 编码
   *
   * @param file        文件
   * @param description 是否包含描述
   * @return base64字符串
   * @throws IOException IO异常
   */
  public static String encode(File file, boolean description) throws IOException {
    String base64 = Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file));
    return description ? DESCRIPTION_TEMPLATE.formatted(Files.probeContentType(file.toPath()), base64) : base64;
  }

  /**
   * 解码
   *
   * @param base64 base64字符串
   * @param file   文件
   * @throws IOException IO异常
   */
  public static void decode(String base64, File file) throws IOException {
    String needToDecode = base64.contains(DESCRIPTION_MARK)
      ? base64.substring(base64.indexOf(DESCRIPTION_MARK) + DESCRIPTION_MARK.length())
      : base64;
    FileUtils.writeByteArrayToFile(file, Base64.getDecoder().decode(needToDecode));
  }
}
