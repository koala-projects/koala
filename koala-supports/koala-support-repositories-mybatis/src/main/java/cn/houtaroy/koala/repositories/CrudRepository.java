package cn.houtaroy.koala.repositories;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @param <IdType> 主键类型
 * @param <T>      实体类型
 * @author Houtaroy
 */
public interface CrudRepository<IdType, T> {

  /**
   * 查询全部
   *
   * @param parameters 查询参数
   * @param pageable   分页参数
   * @return 数据列表
   */
  List<T> findAll(@Param("parameters") Map<String, Object> parameters, @Param("pageable") Pageable pageable);

  /**
   * 根据id查询
   *
   * @param id id
   * @return 数据实体
   */
  Optional<T> findById(IdType id);

  /**
   * 新增
   *
   * @param entity 数据实体
   */
  void add(T entity);

  /**
   * 更新
   *
   * @param entity 数据实体
   */
  void update(T entity);

  /**
   * 删除
   *
   * @param entity 数据实体
   */
  void delete(T entity);
}
