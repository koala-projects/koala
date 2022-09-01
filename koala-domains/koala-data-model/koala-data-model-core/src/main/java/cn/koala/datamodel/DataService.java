package cn.koala.datamodel;

import java.util.Map;
import java.util.Optional;

/**
 * @author Houtaroy
 */
public interface DataService {
  /**
   * 查看数据, 以Map对象返回
   *
   * @param id 数据id
   * @return 数据
   */
  Optional<Map<String, Object>> load(String id);

  /**
   * 新增数据
   *
   * @param metaData 元数据
   * @param contents 所有数据内容
   */
  void add(MetaData metaData, Map<String, Object> contents);

  /**
   * 更新数据
   *
   * @param id       数据id
   * @param contents 所有数据内容
   */
  void update(String id, Map<String, Object> contents);

  /**
   * 删除数据
   *
   * @param id 数据id
   */
  void delete(String id);
}
