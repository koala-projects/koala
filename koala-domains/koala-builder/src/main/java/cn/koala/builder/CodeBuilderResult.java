package cn.koala.builder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 代码模板渲染结果
 *
 * @author Houtaroy
 */
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Data
@Schema(name = "代码生成结果")
public class CodeBuilderResult {
  @Schema(name = "文件名称")
  private String name;
  @Schema(name = "代码内容")
  private String content;
}
