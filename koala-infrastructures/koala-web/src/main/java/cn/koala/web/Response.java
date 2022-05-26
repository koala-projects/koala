package cn.koala.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author Houtaroy
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Response {
  public static final Response SUCCESS = Response.of(HttpStatus.OK.value(), "请求成功");

  protected Integer code;
  protected String message;

  /**
   * 创建成功响应
   *
   * @param message 消息
   * @return 响应实体
   */
  public static Response ok(String message) {
    return of(HttpStatus.OK.value(), message);
  }

  /**
   * 创建错误响应
   *
   * @param message 消息
   * @return 响应实体
   */
  public static Response error(String message) {
    return of(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
  }

  /**
   * 创建响应
   *
   * @param code    代码
   * @param message 消息
   * @return 响应实体
   */
  public static Response of(Integer code, String message) {
    return new Response(code, message);
  }
}
