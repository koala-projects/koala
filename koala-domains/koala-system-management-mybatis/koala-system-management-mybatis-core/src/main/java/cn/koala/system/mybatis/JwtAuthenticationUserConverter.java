package cn.koala.system.mybatis;

import cn.koala.system.AuthenticationUserConverter;
import cn.koala.system.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.util.StringUtils;

/**
 * Jwt认证信息用户转换器
 *
 * @author Houtaroy
 */
public class JwtAuthenticationUserConverter implements AuthenticationUserConverter {

  @Override
  public User apply(Authentication authentication) {
    UserEntity result = null;
    if (authentication instanceof JwtAuthenticationToken token) {
      String id = token.getTokenAttributes().get("user_id").toString();
      String name = token.getName();
      if (StringUtils.hasLength(id) && StringUtils.hasLength(name)) {
        result = UserEntity.builder().id(id).name(name).build();
      }
    }
    return result;
  }
}
