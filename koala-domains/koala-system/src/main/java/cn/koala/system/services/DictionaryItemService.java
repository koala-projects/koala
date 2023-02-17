package cn.koala.system.services;

import cn.koala.mybatis.CrudService;
import cn.koala.mybatis.PagingService;
import cn.koala.system.DictionaryItem;

/**
 * 字典项服务接口
 *
 * @author Houtaroy
 */
public interface DictionaryItemService extends CrudService<DictionaryItem, Long>, PagingService<DictionaryItem, Long> {
}
