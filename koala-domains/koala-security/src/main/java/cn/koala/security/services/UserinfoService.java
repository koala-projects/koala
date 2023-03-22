package cn.koala.security.services;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户信息服务接口
 *
 * @author Houtaroy
 */
public interface UserinfoService {
  UserDetails getUserinfo();

  void changePassword(String password, String newPassword);
}
