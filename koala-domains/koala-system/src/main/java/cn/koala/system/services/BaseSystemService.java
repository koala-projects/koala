package cn.koala.system.services;

import cn.koala.mybatis.BaseService;
import cn.koala.mybatis.CrudRepository;
import cn.koala.mybatis.IdModel;

/**
 * 基础系统服务抽象类
 *
 * @author Houtaroy
 */
public abstract class BaseSystemService<T extends IdModel<Long>> extends BaseService<T, Long> {
  /**
   * 基础系统服务抽象类构造函数
   *
   * @param repository 仓库接口
   */
  public BaseSystemService(CrudRepository<T, Long> repository) {
    super(repository, (entity) -> null);
  }
}
