package cn.koala.office;

import com.alibaba.excel.EasyExcel;

import java.io.InputStream;
import java.util.List;

/**
 * EasyExcel读取器
 *
 * @author Houtaroy
 */
public class EasyExcelReader implements ExcelWebReader {

  @Override
  public <T> List<T> read(String filePathName, Class<T> tClass) {
    return EasyExcel.read(filePathName, tClass, null).sheet().doReadSync();
  }

  @Override
  public <T> List<T> read(InputStream inputStream, Class<T> tClass) {
    return EasyExcel.read(inputStream, tClass, null).sheet().doReadSync();
  }
}
