package cn.houtaroy.koala.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @param <T> 返回数据类型
 * @author Houtaroy
 */
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
public class DataResponse<T> extends Response {

  private T data;

  /**
   * DataResponse构建器
   *
   * @param code    代码
   * @param message 消息
   * @param data    数据
   */
  @Builder(builderMethodName = "dataResultBuilder")
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
    return of(SUCCESS_CODE, "成功", data);
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
    return DataResponse.<T>dataResultBuilder().code(code).message(message).data(data).build();
  }
}
