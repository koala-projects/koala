package cn.koala.authorization.support;

import cn.koala.authorization.repository.KoalaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 默认用户信息服务实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class DefaultUserDetailsService implements UserDetailsService {

  private final KoalaUserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("未找到指定用户"));
  }
}
