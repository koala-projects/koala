package cn.koala.datamodel;

import cn.koala.persistence.Codeable;

import java.util.Optional;

/**
 * 属性
 *
 * @author Houtaroy
 */
public interface Property extends Codeable {

  /**
   * 获取属性类型
   *
   * @return 属性类型
   */
  PropertyType getType();

  /**
   * 获取元数据
   *
   * @return 元数据
   */
  MetaData getMetaData();

  /**
   * 解析数据
   *
   * @param content 数据内容
   * @return 解析后的数据内容
   */
  default Object parse(Object content) {
    return Optional.ofNullable(content)
      .map(Object::toString)
      .map(data -> getType().getParser().apply(data))
      .orElse(null);
  }
}
