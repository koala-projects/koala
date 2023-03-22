package cn.koala.system.entities;

import cn.koala.mybatis.BaseUniversalEntity;
import cn.koala.system.Setting;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "设置数据实体")
public class SettingEntity extends BaseUniversalEntity implements Setting {
  @Schema(description = "设置代码")
  protected String code;
  @Schema(description = "设置名称")
  protected String name;
  @Schema(description = "设置内容")
  protected String content;
  @Schema(description = "设置备注")
  protected String remark;
}
