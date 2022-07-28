package cn.koala.office;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Excel Web服务类
 *
 * @author Houtaroy
 */
public interface WebExcelService extends ExcelService {

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
