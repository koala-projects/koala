package cn.koala.system.mybatis;

import cn.koala.system.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author Houtaroy
 */
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
    return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
      .filter(authentication -> authentication instanceof JwtAuthenticationToken)
      .map(JwtAuthenticationToken.class::cast)
      .filter(authentication -> StringUtils.hasLength(authentication.getName()))
      .filter(authentication -> StringUtils.hasLength(authentication.getTokenAttributes().get("user_id").toString()))
      .map(authentication ->
        UserEntity.builder().id(authentication.getTokenAttributes().get("user_id").toString())
          .name(authentication.getName()).build()
      );
  }
}
