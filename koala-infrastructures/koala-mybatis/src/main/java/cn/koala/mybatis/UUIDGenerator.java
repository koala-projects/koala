package cn.koala.mybatis;

import cn.koala.persistence.Idable;

import java.util.UUID;

/**
 * UUID主键生成器
 *
 * @param <E> 数据实体类型
 * @author Houtaroy
 */
public class UUIDGenerator<E extends Idable<String>> implements IdGenerator<E, String> {
  @Override
  public String apply(E entity) {
    return UUID.randomUUID().toString();
  }
}
