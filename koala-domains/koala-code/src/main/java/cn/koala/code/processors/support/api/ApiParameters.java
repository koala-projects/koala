package cn.koala.code.processors.support.api;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 接口参数合集
 *
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public class ApiParameters {
  private ApiParameter id;
  private List<ApiParameter> others;
}
