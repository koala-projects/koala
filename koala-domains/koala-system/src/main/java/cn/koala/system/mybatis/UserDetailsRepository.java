package cn.koala.system.mybatis;

import cn.koala.system.UserDetailsEntity;
import org.apache.ibatis.annotations.Param;

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
  UserDetailsEntity findByUsername(@Param("username") String username);
}
