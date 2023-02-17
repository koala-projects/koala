package cn.koala.system.repositories;

import cn.koala.mybatis.CrudRepository;
import cn.koala.system.DictionaryItem;

/**
 * 字典项仓库接口
 *
 * @author Houtaroy
 */
public interface DictionaryItemRepository extends CrudRepository<DictionaryItem, Long> {
}
