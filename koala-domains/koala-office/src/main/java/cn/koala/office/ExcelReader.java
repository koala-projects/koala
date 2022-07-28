package cn.koala.office;

import java.io.InputStream;
import java.util.List;

/**
 * Excel读取器
 *
 * @author Houtaroy
 */
public interface ExcelReader {

  /**
   * 读取Excel数据
   *
   * @param filePathName 文件全路径名
   * @param tClass       数据类型
   * @param <T>          数据类型
   * @return 数据列表
   */
  <T> List<T> read(String filePathName, Class<T> tClass);

  /**
   * 读取Excel数据
   *
   * @param inputStream 输入流
   * @param tClass      数据类型
   * @param <T>         数据类型
   * @return 数据列表
   */
  <T> List<T> read(InputStream inputStream, Class<T> tClass);
}
