package cn.koala.mybatis;

import cn.koala.persistence.Idable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 考拉抽象服务类, 使用UUID作为主键生成器
 *
 * @param <E> 实体类型
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractKoalaService<E extends Idable<String>> extends AbstractSmartService<String, E> {
  protected final IdGenerator<E, String> idGenerator = new UUIDGenerator<>();
}
