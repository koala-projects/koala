package cn.koala.office;

import java.io.File;

/**
 * office转换器
 *
 * @author Houtaroy
 */
public interface OfficeConverter {

  /**
   * 文档转换为html
   *
   * @param source 源文件
   * @param target 目标文件
   * @throws Exception 转换异常
   */
  void doc2html(File source, File target) throws Exception;

  /**
   * 表格转换为html
   *
   * @param source 源文件
   * @param target 目标文件
   * @throws Exception 转换异常
   */
  void excel2html(File source, File target) throws Exception;
}
