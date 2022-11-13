package cn.koala.system.mybatis;

import cn.koala.mybatis.PageRepository;
import cn.koala.system.DictionaryItem;
import org.apache.ibatis.annotations.Param;

/**
 * 字典存储库
 *
 * @author Houtaroy
 */
public interface DictionaryItemRepository extends PageRepository<String, DictionaryItem> {
  /**
   * 根据字典ID删除
   *
   * @param dictionaryId 字典ID
   */
  void deleteByDictionaryId(@Param("dictionaryId") String dictionaryId);
}
