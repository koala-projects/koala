package cn.koala.office;

/**
 * Excel Web服务
 *
 * @author Houtaroy
 */
public interface ExcelWebService extends ExcelService {

  /**
   * 获取Excel Web读取器
   *
   * @return Excel Web读取器
   */
  @Override
  ExcelWebReader getReader();

  /**
   * 获取Excel Web写入器
   *
   * @return Excel Web写入器
   */
  @Override
  ExcelWebWriter getWriter();
}
