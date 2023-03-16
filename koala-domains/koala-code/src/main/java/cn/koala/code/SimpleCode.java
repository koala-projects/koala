package cn.koala.code;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 代码简易实现类
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "简易代码")
public class SimpleCode implements Code {
  @Schema(description = "代码名称")
  String name;
  @Schema(description = "代码内容")
  String content;
}
