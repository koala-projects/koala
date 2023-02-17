package cn.koala.mybatis;

import lombok.extern.slf4j.Slf4j;

/**
 * 增删改查帮助类
 *
 * @author Houtaroy
 */
@Slf4j
public abstract class CrudHelper {
  private static AuditorIdSupplier<?> auditorIdSupplier;

  /**
   * 获取审计人员ID
   *
   * @param <ID> 审计人员ID类型
   * @return 审计人员ID
   */
  public static <ID> ID getAuditorId() {
    if (auditorIdSupplier == null) {
      LOGGER.warn("未设置审计员ID提供者或设置为null, 将默认返回审计员ID为null");
      return null;
    }
    return (ID) auditorIdSupplier.get();
  }

  /**
   * 设置审计人员ID提供者
   *
   * @param supplier 审计人员ID提供者
   */
  public static void setAuditorIdSupplier(AuditorIdSupplier<?> supplier) {
    auditorIdSupplier = supplier;
  }
}
