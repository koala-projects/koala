package cn.koala.datamodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Houtaroy
 */
public interface Data {

  /**
   * 获取数据元列表
   *
   * @param <T> 数据元类型
   * @return 数据元列表
   */
  <T extends DataElement> List<T> getElements();

  /**
   * 获取元数据
   *
   * @return 元数据
   */
  MetaData getMetaData();

  /**
   * 转换为Map
   *
   * @return Map类型的数据
   */
  default Map<String, Object> toMap() {
    Map<String, Object> result = new HashMap<>(getElements().size());
    getElements().forEach(element -> result.put(element.getCode(), element.toData()));
    return result;
  }
}
