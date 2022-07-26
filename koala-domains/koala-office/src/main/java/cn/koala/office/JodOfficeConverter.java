package cn.koala.office;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jodconverter.core.DocumentConverter;
import org.jodconverter.core.office.OfficeException;

import java.io.File;

/**
 * Jod office转换器
 *
 * @author Houtaroy
 */
@Getter
@RequiredArgsConstructor
public class JodOfficeConverter implements OfficeConverter {

  protected final DocumentConverter jodConverter;

  @Override
  public void doc2html(File source, File target) throws OfficeException {
    convert(source, target);
  }

  @Override
  public void excel2html(File source, File target) throws OfficeException {
    convert(source, target);
  }

  /**
   * 转换文件
   *
   * @param source 源文件
   * @param target 目标文件
   * @throws OfficeException OfficeException
   */
  public void convert(File source, File target) throws OfficeException {
    jodConverter.convert(source).to(target).execute();
  }
}
