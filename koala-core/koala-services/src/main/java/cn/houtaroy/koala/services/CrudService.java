package cn.houtaroy.koala.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @param <IdType> 主键类型
 * @param <T>      实体类型
 * @author Houtaroy
 */
public interface CrudService<IdType, T> {

  /**
   * 分页列表查询
   *
   * @param parameters 查询参数
   * @param pageable   分页参数
   * @return 查询结果
   */
  Page<T> list(Map<String, Object> parameters, Pageable pageable);

  /**
   * 列表查询
   *
   * @param parameters 查询参数
   * @return 查询结果
   */
  List<T> list(Map<String, Object> parameters);

  /**
   * 根据id查询
   *
   * @param id id
   * @return 查询结果
   */
  T load(IdType id);

  /**
   * 新增
   *
   * @param entity 实体
   */
  void add(T entity);

  /**
   * 更新
   *
   * @param entity 实体
   */
  void update(T entity);

  /**
   * 删除
   *
   * @param entity 实体
   */
  void delete(T entity);
}
