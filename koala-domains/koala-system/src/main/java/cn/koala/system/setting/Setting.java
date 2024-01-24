package cn.koala.system.setting;

import cn.koala.data.domain.Auditable;
import cn.koala.data.domain.Enableable;
import cn.koala.data.domain.Sortable;
import cn.koala.data.domain.Systemic;

/**
 * 设置接口
 *
 * @author Houtaroy
 */
public interface Setting extends Auditable<Long, Long>, Sortable, Enableable, Systemic {

  String getCode();

  String getName();

  String getDescription();

  SettingType getType();

  String getValue();

  void setValue(String value);
}
