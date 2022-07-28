package cn.koala.office;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Excel服务类
 *
 * @author Houtaroy
 */
public interface ExcelService {

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
