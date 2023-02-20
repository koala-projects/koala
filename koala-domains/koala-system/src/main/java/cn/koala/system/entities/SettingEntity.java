package cn.koala.system.entities;

import cn.koala.system.Setting;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 设置数据实体
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class SettingEntity extends BaseSystemEntity implements Setting {
  protected String code;
  protected String name;
  protected String content;
  protected String remark;
}
