package cn.koala.codegen.support;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 代码请求
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@Schema(description = "代码生成请求")
public class CodeGenRequest {

  @Schema(description = "数据库id")
  private Long databaseId;

  @Schema(description = "数据表列表")
  private List<String> tables;

  @Schema(description = "模板组id")
  private Long templateGroupId;
}
