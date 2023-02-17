package cn.koala.security;

import cn.koala.security.repositories.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

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
    Optional<UserDetailsImpl> user = repository.findByUsername(username);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("未找到指定用户");
    }
    return repository.findByUsername(username).orElse(null);
  }
}
