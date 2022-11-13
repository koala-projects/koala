package cn.koala.system.mybatis;

import cn.koala.mybatis.AbstractSmartService;
import cn.koala.mybatis.IdGenerator;
import cn.koala.mybatis.UUIDGenerator;
import cn.koala.persistence.Idable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 抽象系统管理服务类, ID统一为UUID
 *
 * @param <E> 数据实体类型
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractSystemService<E extends Idable<String>> extends AbstractSmartService<String, E> {
  protected final IdGenerator<E, String> idGenerator = new UUIDGenerator<>();
}
