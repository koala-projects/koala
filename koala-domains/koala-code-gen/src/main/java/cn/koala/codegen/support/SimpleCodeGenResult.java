package cn.koala.codegen.support;

import cn.koala.codegen.CodeGenResult;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 简易代码生成结果
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "简易代码生成结果")
public class SimpleCodeGenResult implements CodeGenResult {

  @Schema(description = "路径文件名")
  String pathname;
  @Schema(description = "生成内容")
  String content;
}
