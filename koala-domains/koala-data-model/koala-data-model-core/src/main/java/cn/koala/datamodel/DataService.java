package cn.koala.datamodel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Optional;

/**
 * @author Houtaroy
 */
public interface DataService {
  /**
   * 查询数据
   *
   * @param parameters 查询参数
   * @param pageable   分页参数
   * @return 数据分页列表
   */
  Page<PersistentData> list(Map<String, Object> parameters, Pageable pageable);

  /**
   * 查看数据, 以Map对象返回
   *
   * @param id 数据id
   * @return 数据
   */
  Optional<PersistentData> load(String id);

  /**
   * 新增数据
   *
   * @param metaData 元数据
   * @param contents 所有数据内容
   */
  void add(PersistentMetadata metaData, Map<String, Object> contents);

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
