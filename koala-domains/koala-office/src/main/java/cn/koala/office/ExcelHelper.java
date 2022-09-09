package cn.koala.office;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * Excel帮助类
 *
 * @author Houtaroy
 */
public abstract class ExcelHelper {

  /**
   * 准备HTTP响应
   *
   * @param response HTTP响应
   * @param filename 文件名
   */
  public static void prepareResponse(HttpServletResponse response, String filename) {
    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    response.setCharacterEncoding(StandardCharsets.UTF_8.name());
    response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + filename);
  }
}
