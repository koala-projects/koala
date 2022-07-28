package cn.koala.office;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Excel Web读取器
 *
 * @author Houtaroy
 */
public interface ExcelWebReader extends ExcelReader {

  /**
   * 从上传的Excel文件中读取数据
   *
   * @param multipartFile 上传的Excel文件
   * @param tClass        数据类型
   * @param <T>           数据类型
   * @return 数据列表
   * @throws IOException IOException
   */
  default <T> List<T> read(MultipartFile multipartFile, Class<T> tClass) throws IOException {
    return read(multipartFile.getInputStream(), tClass);
  }
}
