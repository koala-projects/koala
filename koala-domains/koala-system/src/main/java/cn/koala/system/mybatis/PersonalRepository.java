package cn.koala.system.mybatis;

import org.apache.ibatis.annotations.Param;

/**
 * @author Houtaroy
 */
public interface PersonalRepository {
  /**
   * 修改用户密码
   *
   * @param username 用户名
   * @param password 密码
   */
  void changePassword(@Param("username") String username, @Param("password") String password);
}
