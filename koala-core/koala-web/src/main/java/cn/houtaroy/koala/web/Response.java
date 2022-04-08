package cn.houtaroy.koala.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Houtaroy
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Response {

  public static final int SUCCESS_CODE = 200;
  public static final int ERROR_CODE = 500;
  public static final Response SUCCESS = Response.of(SUCCESS_CODE, "成功");

  protected Integer code;
  protected String message;

  /**
   * 创建成功响应
   *
   * @param message 消息
   * @return 响应实体
   */
  public static Response ok(String message) {
    return of(SUCCESS_CODE, message);
  }

  /**
   * 创建错误响应
   *
   * @param message 消息
   * @return 响应实体
   */
  public static Response error(String message) {
    return of(ERROR_CODE, message);
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
