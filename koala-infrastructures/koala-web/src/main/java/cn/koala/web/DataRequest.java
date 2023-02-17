package cn.koala.web;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 数据请求
 * <p>
 * 用于处理某些数组类请求, 例如ID数组
 *
 * @param <T> 请求数据类型
 * @author Houtaroy
 */
@NoArgsConstructor
@Getter
public class DataRequest<T> {
  protected T data;
}
