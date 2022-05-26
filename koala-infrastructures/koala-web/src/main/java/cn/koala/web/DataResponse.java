package cn.koala.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @param <T> 返回数据类型
 * @author Houtaroy
 */
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class DataResponse<T> extends Response {
  protected T data;

  /**
   * 构造函数
   *
   * @param code    代码
   * @param message 消息
   * @param data    数据
   */
  public DataResponse(Integer code, String message, T data) {
    super(code, message);
    this.data = data;
  }

  /**
   * 成功响应
   *
   * @param data 数据
   * @param <T>  数据类型
   * @return 数据响应实体
   */
  public static <T> DataResponse<T> ok(T data) {
    return of(HttpStatus.OK.value(), "请求成功", data);
  }

  /**
   * 创建数据响应
   *
   * @param code    代码
   * @param message 消息
   * @param data    数据
   * @param <T>     数据类型
   * @return 数据响应实体
   */
  public static <T> DataResponse<T> of(Integer code, String message, T data) {
    return new DataResponse<>(code, message, data);
  }
}
