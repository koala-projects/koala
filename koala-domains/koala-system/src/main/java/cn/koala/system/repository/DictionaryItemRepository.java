package cn.koala.system.repository;

import cn.koala.persist.CrudRepository;
import cn.koala.system.model.DictionaryItem;

/**
 * 字典项仓库接口
 *
 * @author Houtaroy
 */
public interface DictionaryItemRepository extends CrudRepository<DictionaryItem, Long> {
}
