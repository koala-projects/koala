package cn.koala.system.mybatis;

import cn.koala.mybatis.PageRepository;
import cn.koala.system.User;

/**
 * @author Houtaroy
 */
public interface UserRepository extends PageRepository<String, User> {
  /**
   * 根据用户名查询用户
   *
   * @param username 用户名
   * @return 用户
   */
  User findByUsername(String username);
}
