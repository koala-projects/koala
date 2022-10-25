package cn.koala.system.mybatis;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色关系存储库
 *
 * @author Houtaroy
 */
public interface UserRoleRepository {

  /**
   * 根据用户id查询角色id列表
   *
   * @param userId 用户id
   * @return 角色id列表
   */
  List<String> findAllRoleIdByUserId(@Param("userId") String userId);

  /**
   * 新增用户角色关系
   *
   * @param userId  用户id
   * @param roleIds 角色id列表
   */
  void add(@Param("userId") String userId, @Param("roleIds") List<String> roleIds);

  /**
   * 根据用户id删除用户角色关系
   *
   * @param userId 角色id
   */
  void deleteByUserId(@Param("userId") String userId);
}
