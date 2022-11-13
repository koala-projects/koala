package cn.koala.system.mybatis;

import cn.koala.system.Dictionary;
import cn.koala.system.DictionaryService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * 字典服务MyBatis实现
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class DictionaryServiceImpl extends AbstractSystemService<Dictionary> implements DictionaryService {
  protected final DictionaryRepository repository;
  protected final DictionaryItemRepository dictionaryItemRepository;

  @Override
  public void delete(Dictionary entity) {
    dictionaryItemRepository.deleteByDictionaryId(entity.getId());
    super.delete(entity);
  }
}
