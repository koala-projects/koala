package cn.koala.security.services;

import cn.koala.security.repositories.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * UserDetailsService实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  protected final UserDetailsRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("未找到指定用户"));
  }
}
