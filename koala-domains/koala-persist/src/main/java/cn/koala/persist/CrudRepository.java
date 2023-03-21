package cn.koala.persist;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 增删改查仓库接口
 *
 * @author Houtaroy
 */
public interface CrudRepository<T, ID> {
  /**
   * 查询全部数据
   *
   * @param parameters 查询参数
   * @return 数据列表
   */
  List<T> find(Map<String, Object> parameters);

  /**
   * 根据id查询数据
   *
   * @param id 主键
   * @return 数据实体
   */
  Optional<T> findById(ID id);

  /**
   * 插入数据
   *
   * @param entity    数据实体
   * @param <S>数据实体类型
   */
  <S extends T> void add(S entity);

  /**
   * 根据id更新数据
   *
   * @param entity 数据实体
   * @param <S>    数据实体类型
   */
  <S extends T> void update(S entity);

  /**
   * 根据id删除数据
   *
   * @param entity 数据实体
   */
  <S extends T> void delete(S entity);
}
