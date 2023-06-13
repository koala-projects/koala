package cn.koala.security.userdetails.support;

import cn.koala.security.userdetails.CacheableUserDetailsService;
import cn.koala.security.userdetails.repository.UserDetailsRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.util.Map;

/**
 * UserDetailsService实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class DefaultUserDetailsService implements CacheableUserDetailsService, ApplicationListener<UserDetailsCacheEvictEvent> {

  protected final UserDetailsRepository repository;
  protected final Map<String, UserDetails> cache = new ConcurrentReferenceHashMap<>();

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return loadUserByUsername(username, false);
  }

  @Override
  public UserDetails loadUserByUsername(String username, boolean useCache) {
    if (!useCache || !cache.containsKey(username)) {
      UserDetails userDetails = this.repository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("未找到指定用户"));
      cache.put(username, userDetails);
    }
    return cache.get(username);
  }

  @Override
  public void onApplicationEvent(@NotNull UserDetailsCacheEvictEvent event) {
    event.getUsernames().forEach(cache::remove);
  }
}
