package cn.koala.security.userdetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 可缓存的用户详情服务类
 *
 * @author Houtaroy
 */
public interface CacheableUserDetailsService extends UserDetailsService {

  UserDetails loadUserByUsername(String username, boolean useCache);
}
