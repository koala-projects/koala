package cn.koala.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @param <K> 主键类型
 * @param <V> 实体类型
 * @author Houtaroy
 */
public interface CrudService<K, V> {
  /**
   * 分页列表查询
   *
   * @param parameters 查询参数
   * @param pageable   分页参数
   * @return 查询结果
   */
  Page<V> list(Map<String, Object> parameters, Pageable pageable);

  /**
   * 列表查询
   *
   * @param parameters 查询参数
   * @return 查询结果
   */
  List<V> list(Map<String, Object> parameters);

  /**
   * 根据id查询
   *
   * @param id id
   * @return 查询结果
   */
  Optional<V> load(K id);

  /**
   * 新增
   *
   * @param entity 实体
   */
  void add(V entity);

  /**
   * 更新
   *
   * @param entity 实体
   */
  void update(V entity);

  /**
   * 删除
   *
   * @param entity 实体
   */
  void delete(V entity);
}
