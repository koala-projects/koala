package cn.koala.system.mybatis;

import cn.koala.mybatis.AbstractUUIDCrudService;
import cn.koala.system.DictionaryItem;
import cn.koala.system.DictionaryItemService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * 字典项服务MyBatis实现
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class MyBatisDictionaryItemService extends AbstractUUIDCrudService<DictionaryItem>
  implements DictionaryItemService {
  protected final DictionaryItemRepository repository;
}