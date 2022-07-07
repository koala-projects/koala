package cn.koala.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * SpringSecurity帮助类
 *
 * @author Houtaroy
 */
@Slf4j
public final class SpringSecurityHelper {

  private static AuthenticationUserConverter converter;

  private SpringSecurityHelper() {
    throw new IllegalStateException("工具类不允许实例化");
  }

  /**
   * 设置转换器
   *
   * @param converter 认证信息用户转换器
   */
  public static void setConverter(AuthenticationUserConverter converter) {
    SpringSecurityHelper.converter = converter;
  }

  /**
   * 当前用户
   *
   * @return 用户
   */
  public static Optional<User> currentUser() {
    if (converter == null) {
      LOGGER.warn("未找到AuthenticationUserConverter, 请手动实现或设置");
      return Optional.empty();
    }
    return Optional.of(converter.apply(SecurityContextHolder.getContext().getAuthentication()));
  }
}
