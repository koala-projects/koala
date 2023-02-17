package cn.koala.system.services;

import cn.koala.system.Dictionary;
import cn.koala.system.repositories.DictionaryRepository;

/**
 * 字典服务实现类
 *
 * @author Houtaroy
 */
public class DictionaryServiceImpl extends BaseSystemService<Dictionary> implements DictionaryService {
  /**
   * 字典服务实现类构造函数
   *
   * @param repository 字典仓库接口
   */
  public DictionaryServiceImpl(DictionaryRepository repository) {
    super(repository);
  }
}
