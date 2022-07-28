package cn.koala.office;

/**
 * Excel服务类
 *
 * @author Houtaroy
 */
public interface ExcelService {

  /**
   * 获取Excel读取器
   *
   * @return Excel读取器
   */
  ExcelReader getReader();

  /**
   * 获取Excel读取器
   *
   * @return Excel读取器
   */
  ExcelWriter getWriter();
}
