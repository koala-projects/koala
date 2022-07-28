package cn.koala.office;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 默认Excel Web服务
 *
 * @author Houtaroy
 */
@Getter
@RequiredArgsConstructor
@SuppressWarnings("PMD")
public class DefaultExcelWebService implements ExcelWebService {
  protected final ExcelWebReader reader;
  protected final ExcelWebWriter writer;
}
