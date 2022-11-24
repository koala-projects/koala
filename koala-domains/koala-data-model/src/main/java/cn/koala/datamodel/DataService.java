package cn.koala.datamodel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 数据服务
 *
 * @author Houtaroy
 */
public interface DataService {
  /**
   * 数据列表分页查询
   *
   * @param parameters 查询参数
   * @param pageable   分页参数
   * @return 查询结果
   */
  Page<Map<String, Object>> list(Map<String, Object> parameters, Pageable pageable);

  /**
   * 数据列表查询
   *
   * @param parameters 查询参数
   * @return 查询结果
   */
  List<Map<String, Object>> list(Map<String, Object> parameters);

  /**
   * 根据id查询
   *
   * @param id id
   * @return 查询结果
   */
  Optional<Map<String, Object>> load(String id);

  /**
   * 新增
   *
   * @param metadata 元数据
   * @param data     数据
   */
  void add(Metadata metadata, Map<String, Object> data);

  /**
   * 更新
   *
   * @param id   数据记录ID
   * @param data 数据
   */
  void update(String id, Map<String, Object> data);

  /**
   * 删除
   *
   * @param id 数据记录ID
   */
  void delete(String id);
}
