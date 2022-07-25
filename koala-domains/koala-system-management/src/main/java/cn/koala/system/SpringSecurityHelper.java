package cn.koala.system;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * SpringSecurity帮助类
 *
 * @author Houtaroy
 */
@Slf4j
public final class SpringSecurityHelper {

  private SpringSecurityHelper() {
    throw new IllegalStateException("工具类不允许实例化");
  }

  /**
   * 当前用户
   *
   * @return 用户
   */
  public static Optional<User> currentUser() {
    return Optional.empty();
  }
}
