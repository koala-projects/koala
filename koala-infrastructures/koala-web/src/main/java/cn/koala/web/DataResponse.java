package cn.koala.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * 数据响应类
 *
 * @param <T> 返回数据类型
 * @author Houtaroy
 */
@NoArgsConstructor
@Getter
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
    return new DataResponse<>(HttpStatus.OK.value(), "请求成功", data);
  }
}
