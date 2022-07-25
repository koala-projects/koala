package cn.koala.system.mybatis;

import cn.koala.system.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;

/**
 * SpringSecurity帮助类
 *
 * @author Houtaroy
 */
@Slf4j
public final class JwtUtil {

  private JwtUtil() {
    throw new IllegalStateException("工具类不允许实例化");
  }

  /**
   * 当前用户
   *
   * @return 用户
   */
  public static Optional<User> currentUser() {
    return Optional.ofNullable(SecurityContextHolder.getContext())
      .map(SecurityContext::getAuthentication)
      .map(Authentication::getCredentials)
      .filter(credentials -> credentials instanceof Jwt)
      .map(Jwt.class::cast)
      .filter(jwt -> jwt.hasClaim("user_id"))
      .map(jwt -> UserEntity.builder().id(jwt.getClaim("user_id")).name(jwt.getSubject()).build());
  }
}
