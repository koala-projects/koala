package cn.koala.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 字典项数据实体
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "字典项数据实体")
public class DictionaryItemEntity extends AbstractSystemEntity implements DictionaryItem {
  @Schema(description = "字典项名称")
  private String name;
  @Schema(description = "字典项内容")
  private String content;
  private DictionaryEntity dictionary;
}
