package cn.koala.system.mybatis;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户详情存储库
 *
 * @author Houtaroy
 */
public interface UserDetailsRepository {
  /**
   * 根据用户名查找用户详情
   *
   * @param username 用户名
   * @return 用户详情
   */
  UserDetails findByUsername(@Param("username") String username);
}
