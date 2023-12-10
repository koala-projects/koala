package cn.koala.persist;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 增删改查服务接口
 *
 * @author Houtaroy
 */
@Deprecated
public interface CrudService<T, ID> {

  /**
   * 分页查询数据
   *
   * @param parameters 查询参数
   * @param pageable   分页参数
   * @return 数据分页列表
   */
  Page<T> page(Map<String, Object> parameters, Pageable pageable);

  /**
   * 根据条件查询数据
   *
   * @param parameters 查询参数
   * @return 数据列表
   */
  List<T> list(Map<String, Object> parameters);

  /**
   * 根据id查询数据
   *
   * @param id id
   * @return 数据实体
   */
  T load(ID id);

  /**
   * 新增数据
   *
   * @param entity    数据实体
   * @param <S>数据实体类型
   */
  <S extends T> void create(@NonNull S entity);

  /**
   * 修改数据
   *
   * @param entity    数据实体
   * @param <S>数据实体类型
   */
  <S extends T> void update(@NonNull S entity);

  /**
   * 删除数据
   *
   * @param entity    数据实体
   * @param <S>数据实体类型
   */
  <S extends T> void delete(@NonNull S entity);
}
