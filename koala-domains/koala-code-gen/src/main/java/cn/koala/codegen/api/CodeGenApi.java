package cn.koala.codegen.api;

import cn.koala.codegen.service.MultiCodeGenResult;
import cn.koala.web.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 代码生成接口
 *
 * @author Houtaroy
 */
@RestController
@RequestMapping("/api/code-gen")
@Tag(name = "03-01 代码生成接口")
@SecurityRequirement(name = "spring-security")
public interface CodeGenApi {

  /**
   * 代码生成预览
   *
   * @param request 代码请求
   * @return 预览结果
   */
  @PreAuthorize("hasAuthority('code-gen.generate')")
  @Operation(operationId = "codeGenPreview", summary = "代码生成预览")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PreviewResult.class))}
  )
  @PostMapping("preview")
  DataResponse<List<MultiCodeGenResult>> preview(@RequestBody CodeGenRequest request);

  /**
   * 代码生成下载
   *
   * @param request 代码请求
   * @return 临时文件路径
   */
  @PreAuthorize("hasAuthority('code-gen.generate')")
  @Operation(operationId = "codeDownload", summary = "代码生成下载")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DownloadResult.class))}
  )
  @PostMapping("download")
  DataResponse<String> download(@RequestBody CodeGenRequest request);

  class PreviewResult extends DataResponse<List<MultiCodeGenResult>> {

  }

  class DownloadResult extends DataResponse<String> {

  }
}
