package cn.koala.system.entities;

import cn.koala.system.DictionaryItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 字典项数据实体类
 *
 * @author Houtaroy
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DictionaryItemEntity extends BaseSystemEntity implements DictionaryItem {
  protected String code;
  protected String name;
  protected String remark;
  protected Long dictionaryId;
}
