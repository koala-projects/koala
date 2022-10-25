package cn.koala.system.mybatis;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 用户详细信息服务实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  protected final UserDetailsRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repository.findByUsername(username);
  }
}
