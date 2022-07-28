package cn.koala.office;

import com.alibaba.excel.EasyExcel;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * EasyExcel服务类
 *
 * @author Houtaroy
 */
@SuppressWarnings("PMD")
public class EasyExcelService extends AbstractWebExcelService {

  @Override
  public <T> List<T> read(String filePathName, Class<T> tClass) {
    return EasyExcel.read(filePathName, tClass, null).sheet().doReadSync();
  }

  @Override
  public <T> List<T> read(InputStream inputStream, Class<T> tClass) {
    return EasyExcel.read(inputStream, tClass, null).sheet().doReadSync();
  }

  @Override
  public <T> void write(String filePathName, List<T> data, Class<T> tClass) {
    EasyExcel.write(filePathName, tClass).sheet().doWrite(data);
  }

  @Override
  public <T> void write(OutputStream outputStream, List<T> data, Class<T> tClass) {
    EasyExcel.write(outputStream, tClass).sheet().doWrite(data);
  }

  @Override
  public void write(String filePathName, List<List<String>> headers, List<LinkedHashMap<String, Object>> data) {
    EasyExcel.write(filePathName).head(headers).sheet()
      .doWrite(data.stream().map(item -> item.values().stream().toList()).toList());
  }

  @Override
  public void write(OutputStream outputStream, List<List<String>> headers, List<LinkedHashMap<String, Object>> data) {
    EasyExcel.write(outputStream).head(headers).sheet()
      .doWrite(data.stream().map(item -> item.values().stream().toList()).toList());
  }

  @Override
  public <T> void template(String templateFilePathName, String filePathName, List<T> data, Class<T> tClass) {
    EasyExcel.write(filePathName, tClass).withTemplate(templateFilePathName).sheet().doFill(data);
  }

  @Override
  public <T> void template(String templateFilePathName, OutputStream outputStream, List<T> data, Class<T> tClass) {
    EasyExcel.write(outputStream, tClass).withTemplate(templateFilePathName).sheet().doFill(data);
  }
}
