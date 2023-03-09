package cn.koala.code.apis;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 代码预览请求类
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@Schema(description = "代码预览请求")
public class CodePreviewRequest {
  @Schema(description = "数据库表名")
  private String table;
  @Schema(description = "模板组名")
  private String group;
}
