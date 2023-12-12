package cn.koala.system.repository;

import cn.koala.mybatis.repository.CrudRepository;
import cn.koala.system.domain.Dictionary;

/**
 * 字典仓库接口
 *
 * @author Houtaroy
 */
public interface DictionaryRepository extends CrudRepository<Dictionary, Long> {
}
