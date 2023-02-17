package cn.koala.system;

import cn.koala.mybatis.AuditModel;
import cn.koala.mybatis.IdModel;
import cn.koala.mybatis.SortModel;
import cn.koala.mybatis.StateModel;

/**
 * 字典项接口
 *
 * @author Houtaroy
 */
public interface DictionaryItem extends IdModel<Long>, SortModel, StateModel, AuditModel<Long> {
  String getCode();

  String getName();

  String getRemark();

  Long getDictionaryId();
}
