package cn.koala.postoffice.support;

import cn.koala.postoffice.Courier;

/**
 * 基于ObjectMapper打包器的驿站抽象类
 *
 * @author Houtaroy
 */
public abstract class AbstractObjectMapperPostOffice<T> extends AbstractPostOffice<T> {

  public AbstractObjectMapperPostOffice(String name, Class<T> parcelClass, Courier<T> courier) {
    super(name, new ObjectMapperPacker<>(parcelClass), courier);
  }
}
