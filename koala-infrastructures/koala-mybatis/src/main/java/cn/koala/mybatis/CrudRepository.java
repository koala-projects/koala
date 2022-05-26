package cn.koala.mybatis;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @param <T> 主键类型
 * @param <E> 实体类型
 * @author Houtaroy
 */
public interface CrudRepository<T, E> {

  /**
   * 查询全部
   *
   * @param parameters 查询参数
   * @return 数据列表
   */
  List<E> findAll(@Param("parameters") Map<String, Object> parameters);

  /**
   * 根据id查询
   *
   * @param id id
   * @return 数据实体
   */
  Optional<E> findById(T id);

  /**
   * 新增
   *
   * @param entity 数据实体
   */
  void add(E entity);

  /**
   * 更新
   *
   * @param entity 数据实体
   */
  void update(E entity);

  /**
   * 删除
   *
   * @param entity 数据实体
   */
  void delete(E entity);
}
