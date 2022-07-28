package cn.koala.office;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Excel Web写入器
 *
 * @author Houtaroy
 */
public interface ExcelWebWriter extends ExcelWriter {

  /**
   * 向HttpServletResponse中写入Excel
   *
   * @param response HttpServletResponse
   * @param filename 文件名
   * @param data     数据列表
   * @param tClass   数据类型
   * @param <T>      数据类型
   * @throws IOException IOException
   */
  <T> void write(HttpServletResponse response, String filename, List<T> data, Class<T> tClass) throws IOException;

  /**
   * 向HttpServletResponse中写入Excel
   *
   * @param response HttpServletResponse
   * @param filename 文件名
   * @param headers  标头列表
   * @param data     数据列表
   * @throws IOException IOException
   */
  void write(
    HttpServletResponse response, String filename, List<List<String>> headers, List<LinkedHashMap<String, Object>> data
  ) throws IOException;

  /**
   * 使用模板填充写入Excel
   *
   * @param templateFilePathName 模板全路径名
   * @param response             HttpServletResponse
   * @param filename             文件名
   * @param data                 数据列表
   * @param tClass               数据类型
   * @param <T>                  数据类型
   * @throws IOException IOException
   */
  <T> void template(
    String templateFilePathName, HttpServletResponse response, String filename, List<T> data, Class<T> tClass
  ) throws IOException;
}
