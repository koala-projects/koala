package cn.koala.web;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 数据请求
 * <p>
 * 用于处理某些数组类请求, 例如ID数组
 *
 * @param <T> 请求数据类型
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DataRequest<T> {
  private T data;
}
