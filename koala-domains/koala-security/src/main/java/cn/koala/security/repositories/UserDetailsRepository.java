package cn.koala.security.repositories;

import cn.koala.security.UserDetailsImpl;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * UserDetails仓库接口
 *
 * @author Houtaroy
 */
public interface UserDetailsRepository {
  /**
   * 根据用户名查询用户
   *
   * @param username 用户名
   * @return 数据实体
   */
  Optional<UserDetailsImpl> findByUsername(String username);

  void changePassword(@Param("username") String username, @Param("password") String password);
}
