package cn.koala.system.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @param <T> 主键类型
 * @param <E> 实体类型
 * @author Houtaroy
 */
public interface CrudService<T, E> {
  /**
   * 分页列表查询
   *
   * @param parameters 查询参数
   * @param pageable   分页参数
   * @return 查询结果
   */
  Page<E> list(Map<String, Object> parameters, Pageable pageable);

  /**
   * 列表查询
   *
   * @param parameters 查询参数
   * @return 查询结果
   */
  List<E> list(Map<String, Object> parameters);

  /**
   * 根据id查询
   *
   * @param id id
   * @return 查询结果
   */
  Optional<E> load(T id);

  /**
   * 新增
   *
   * @param entity 实体
   */
  void add(E entity);

  /**
   * 更新
   *
   * @param entity 实体
   */
  void update(E entity);

  /**
   * 删除
   *
   * @param entity 实体
   */
  void delete(E entity);
}
