package cn.koala.system.repositories;

import org.apache.ibatis.annotations.Param;

/**
 * 用户信息仓库接口
 *
 * @author Houtaroy
 */
public interface UserinfoRepository {

  void changePassword(@Param("username") String username, @Param("password") String password);
}
