package cn.koala.code.apis;

import cn.koala.code.Code;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码接口
 *
 * @author Houtaroy
 */
@RestController
@RequestMapping("/api/code")
@Tag(name = "代码接口")
public interface CodeApi {
  /**
   * 代码预览
   *
   * @param request 代码预览请求
   * @return 字典
   */
  @Operation(operationId = "codePreview", summary = "代码预览")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PreviewResult.class))}
  )
  @PostMapping("preview")
  DataResponse<Map<String, List<Code>>> preview(@RequestBody CodeRequest request);

  class PreviewResult extends HashMap<String, List<Code>> {

  }
}
