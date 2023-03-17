package cn.koala.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * 响应类
 *
 * @author Houtaroy
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Response {
  public static final Response SUCCESS = new Response(HttpStatus.OK.value(), "请求成功");
  public static final Response FORBIDDEN = new Response(HttpStatus.FORBIDDEN.value(), "无访问权限");

  protected Integer code;
  protected String message;

  /**
   * 创建成功响应
   *
   * @param message 消息
   * @return 响应实体
   */
  public static Response ok(String message) {
    return new Response(HttpStatus.OK.value(), message);
  }

  /**
   * 创建错误响应
   *
   * @param message 消息
   * @return 响应实体
   */
  public static Response error(String message) {
    return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
  }
}
