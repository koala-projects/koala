package cn.koala.datamodel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Houtaroy
 */
public interface DataService {
  /**
   * 分页查询数据
   *
   * @param parameters 查询参数
   * @param pageable   分页参数
   * @return 数据分页列表
   */
  Page<Data> list(Map<String, Object> parameters, Pageable pageable);

  /**
   * 查询数据
   *
   * @param parameters 查询参数
   * @return 数据列表
   */
  List<Data> list(Map<String, Object> parameters);

  /**
   * 查看数据, 以Map对象返回
   *
   * @param id 数据id
   * @return 数据
   */
  Optional<Data> load(String id);

  /**
   * 新增数据
   *
   * @param metaData 元数据
   * @param contents 所有数据内容
   */
  void add(Metadata metaData, Map<String, Object> contents);

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
