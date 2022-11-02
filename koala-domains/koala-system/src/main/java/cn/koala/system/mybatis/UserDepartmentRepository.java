package cn.koala.system.mybatis;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户部门关系存储库
 *
 * @author Houtaroy
 */
public interface UserDepartmentRepository {

  /**
   * 根据用户id查询部门id列表
   *
   * @param userId 用户id
   * @return 部门id列表
   */
  List<String> findAllDepartmentIdByUserId(@Param("userId") String userId);

  /**
   * 新增用户部门关系
   *
   * @param userId        用户id
   * @param departmentIds 部门id列表
   */
  void add(@Param("userId") String userId, @Param("departmentIds") List<String> departmentIds);

  /**
   * 根据用户id删除用户部门关系
   *
   * @param userId 部门id
   */
  void deleteByUserId(@Param("userId") String userId);
}
