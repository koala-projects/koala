package cn.koala.datamodel;

import cn.koala.persistence.Idable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据
 *
 * @author Houtaroy
 */
public interface Data extends Idable<String> {
  /**
   * 获取元数据
   *
   * @return 元数据
   */
  Metadata getMetadata();

  /**
   * 获取数据元列表
   *
   * @param <T> 数据元类型
   * @return 数据元列表
   */
  <T extends DataElement> List<T> getElements();

  /**
   * 转换为Map
   *
   * @return Map类型的数据
   */
  default Map<String, Object> toMap() {
    Map<String, Object> result = new HashMap<>(getElements().size());
    getElements().forEach(element -> result.put(
      element.getProperty().getCode(),
      element.getProperty().parse(element.getContent()))
    );
    return result;
  }
}
