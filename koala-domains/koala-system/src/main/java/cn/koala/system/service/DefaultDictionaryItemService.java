package cn.koala.system.service;

import cn.koala.mybatis.service.AbstractSmartService;
import cn.koala.system.domain.DictionaryItem;
import cn.koala.system.repository.DictionaryItemRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 字典项服务实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Getter
public class DefaultDictionaryItemService extends AbstractSmartService<DictionaryItem, Long>
  implements DictionaryItemService {

  private final DictionaryItemRepository repository;
}
