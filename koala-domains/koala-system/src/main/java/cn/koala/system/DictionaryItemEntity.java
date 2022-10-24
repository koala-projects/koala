package cn.koala.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 字典项数据实体
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "字典项数据实体")
public class DictionaryItemEntity implements DictionaryItem {
  @Schema(description = "主键ID")
  private String id;
  @Schema(description = "字典项名称")
  private String name;
  @Schema(description = "字典项内容")
  private String content;
  private DictionaryEntity dictionary;
}
