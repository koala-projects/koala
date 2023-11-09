package cn.koala.system.service;

import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.system.model.DictionaryItem;
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
public class DictionaryItemServiceImpl extends AbstractMyBatisService<DictionaryItem, Long> implements DictionaryItemService {

  protected final DictionaryItemRepository repository;
}
