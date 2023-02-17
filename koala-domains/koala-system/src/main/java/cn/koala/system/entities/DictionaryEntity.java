package cn.koala.system.entities;

import cn.koala.system.Dictionary;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 字典数据实体类
 *
 * @author Houtaroy
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DictionaryEntity extends BaseSystemEntity implements Dictionary {
  protected String code;
  protected String name;
  protected String remark;
}
