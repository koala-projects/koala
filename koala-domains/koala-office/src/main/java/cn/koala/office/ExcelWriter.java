package cn.koala.office;

import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Excel写入器
 *
 * @author Houtaroy
 */
public interface ExcelWriter {
  /**
   * 写入Excel
   *
   * @param filePathName 文件全路径名
   * @param data         数据列表
   * @param tClass       数据类型
   * @param <T>          数据类型
   */
  <T> void write(String filePathName, List<T> data, Class<T> tClass);

  /**
   * 写入Excel
   *
   * @param outputStream 输出流
   * @param data         数据列表
   * @param tClass       数据类型
   * @param <T>          数据类型
   */
  <T> void write(OutputStream outputStream, List<T> data, Class<T> tClass);

  /**
   * 写入Excel
   *
   * @param filePathName 文件名
   * @param headers      标头列表
   * @param data         数据列表
   */
  void write(String filePathName, List<List<String>> headers, List<LinkedHashMap<String, Object>> data);

  /**
   * 写入Excel
   *
   * @param outputStream 输出流
   * @param headers      标头列表
   * @param data         数据列表
   */
  void write(OutputStream outputStream, List<List<String>> headers, List<LinkedHashMap<String, Object>> data);

  /**
   * 使用模板填充写入Excel
   *
   * @param templateFilePathName 模板全路径名
   * @param filePathName         文件全路径名
   * @param data                 数据列表
   * @param tClass               数据类型
   * @param <T>                  数据类型
   */
  <T> void template(String templateFilePathName, String filePathName, List<T> data, Class<T> tClass);

  /**
   * 使用模板填充写入Excel
   *
   * @param templateFilePathName 模板全路径名
   * @param outputStream         输出流
   * @param data                 数据列表
   * @param tClass               数据类型
   * @param <T>                  数据类型
   */
  <T> void template(String templateFilePathName, OutputStream outputStream, List<T> data, Class<T> tClass);
}
