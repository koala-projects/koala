package cn.koala.datamodel;

import cn.koala.persistence.CrudService;

/**
 * @author Houtaroy
 */
public interface MetaDataService extends CrudService<String, MetaData> {
  /**
   * 更新元数据
   *
   * @param entity 元数据对象
   */
  @Override
  default void update(MetaData entity) {
    throw new IllegalStateException("元数据不支持修改");
  }
}
