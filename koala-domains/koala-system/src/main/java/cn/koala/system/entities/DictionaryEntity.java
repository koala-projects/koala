package cn.koala.system.entities;

import cn.koala.mybatis.AbstractEntity;
import cn.koala.system.Dictionary;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "字典数据实体")
public class DictionaryEntity extends AbstractEntity implements Dictionary {
  @Schema(description = "字典代码")
  protected String code;
  @Schema(description = "字典名称")
  protected String name;
  @Schema(description = "字典备注")
  protected String remark;
}
