package cn.koala.system.services;

import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.system.DictionaryItem;
import cn.koala.system.repositories.DictionaryItemRepository;

/**
 * 字典项服务实现类
 *
 * @author Houtaroy
 */
public class DictionaryItemServiceImpl extends AbstractMyBatisService<DictionaryItem, Long> implements DictionaryItemService {
  /**
   * 字典服务实现类构造函数
   *
   * @param repository 字典仓库接口
   */
  public DictionaryItemServiceImpl(DictionaryItemRepository repository) {
    super(repository);
  }
}
