package cn.koala.security.userdetails.repository;

import cn.koala.security.userdetails.support.KoalaUser;
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
  Optional<KoalaUser> findByUsername(String username);

  void changePassword(@Param("username") String username, @Param("password") String password);
}
