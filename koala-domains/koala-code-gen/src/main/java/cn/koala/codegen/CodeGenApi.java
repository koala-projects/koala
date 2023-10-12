package cn.koala.codegen;

import cn.koala.codegen.support.CodeGenRequest;
import cn.koala.codegen.support.SimpleCodeGenResult;
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

import java.util.List;
import java.util.Map;

/**
 * 代码接口
 *
 * @author Houtaroy
 */
@RestController
@RequestMapping("/api/code-gen")
@Tag(name = "03-01 代码生成接口")
public interface CodeGenApi {
  /**
   * 代码生成预览
   *
   * @param request 代码请求
   * @return 预览结果
   */
  @Operation(operationId = "codeGenPreview", summary = "代码生成预览")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PreviewResult.class))}
  )
  @PostMapping("preview")
  DataResponse<Map<String, List<CodeGenResult>>> preview(@RequestBody CodeGenRequest request);

  /**
   * 代码生成下载
   *
   * @param request 代码请求
   * @return 临时文件路径
   */
  @Operation(operationId = "codeDownload", summary = "代码生成下载")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DownloadResult.class))}
  )
  @PostMapping("download")
  DataResponse<String> download(@RequestBody CodeGenRequest request);

  class PreviewResult extends DataResponse<Map<String, List<SimpleCodeGenResult>>> {

  }

  class DownloadResult extends DataResponse<String> {

  }
}
