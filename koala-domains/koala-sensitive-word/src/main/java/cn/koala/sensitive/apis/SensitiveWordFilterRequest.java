package cn.koala.sensitive.apis;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 敏感词过滤请求
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@Schema(description = "敏感词过滤请求")
public class SensitiveWordFilterRequest {
  @Schema(description = "过滤内容")
  private String content;
  @Schema(description = "替换物")
  private Character replacement;
}
