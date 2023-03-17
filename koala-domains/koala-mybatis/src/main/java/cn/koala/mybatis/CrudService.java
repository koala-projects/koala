package cn.koala.mybatis;

import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Map;

/**
 * 增删改查服务接口
 *
 * @author Houtaroy
 */
public interface CrudService<T, ID> {
  /**
   * 新增数据
   *
   * @param entity    数据实体
   * @param <S>数据实体类型
   */
  <S extends T> void add(@NonNull S entity);

  /**
   * 删除数据
   *
   * @param entity    数据实体
   * @param <S>数据实体类型
   */
  <S extends T> void delete(@NonNull S entity);

  /**
   * 修改数据
   *
   * @param entity    数据实体
   * @param <S>数据实体类型
   */
  <S extends T> void update(@NonNull S entity);

  /**
   * 根据id查询数据
   *
   * @param id id
   * @return 数据实体
   */
  T load(ID id);

  /**
   * 根据条件查询数据
   *
   * @param parameters 查询参数
   * @return 数据列表
   */
  List<T> list(Map<String, Object> parameters);


  /**
   * 保存
   *
   * @param entity    数据实体
   * @param <S>数据实体类型
   */
  <S extends T> void save(@NonNull S entity);
}
