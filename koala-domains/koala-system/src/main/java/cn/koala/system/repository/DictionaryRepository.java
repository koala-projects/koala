package cn.koala.system.repository;

import cn.koala.persist.CrudRepository;
import cn.koala.system.model.Dictionary;

/**
 * 字典仓库接口
 *
 * @author Houtaroy
 */
public interface DictionaryRepository extends CrudRepository<Dictionary, Long> {
}
