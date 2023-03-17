package cn.koala.sensitive.apis;

import cn.koala.web.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 代码接口
 *
 * @author Houtaroy
 */
@RestController
@RequestMapping("/api/sensitive-word")
@Tag(name = "敏感词接口")
public interface SensitiveWordApi {
  /**
   * 敏感词过滤
   *
   * @param request 敏感词过滤请求
   * @return 字典
   */
  @Operation(operationId = "sensitiveWordFilter", summary = "敏感词过滤")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FilterResult.class))}
  )
  @PostMapping("filter")
  DataResponse<String> filter(@RequestBody SensitiveWordFilterRequest request);

  class FilterResult extends DataResponse<String> {

  }
}
