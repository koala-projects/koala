package cn.koala.system.repository;

import cn.koala.mybatis.repository.CrudRepository;
import cn.koala.system.domain.DictionaryItem;

/**
 * 字典项仓库接口
 *
 * @author Houtaroy
 */
public interface DictionaryItemRepository extends CrudRepository<DictionaryItem, Long> {
}
