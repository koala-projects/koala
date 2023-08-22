package cn.koala.security.userdetails.support;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户信息帮助类
 *
 * @author Houtaroy
 */
public abstract class UserDetailsHelper {

  public static Collection<SimpleGrantedAuthority> wrap(List<String> authorities) {
    if (CollectionUtils.isEmpty(authorities)) {
      return new HashSet<>(0);
    }
    Set<SimpleGrantedAuthority> result = new HashSet<>(authorities.size());
    authorities.stream().map(SimpleGrantedAuthority::new).forEach(result::add);
    return result;
  }
}
