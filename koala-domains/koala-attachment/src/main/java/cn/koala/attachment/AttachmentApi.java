package cn.koala.attachment;

import cn.koala.openapi.PageableAsQueryParam;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 附件接口
 *
 * @author Koala Code Generator
 */
@RestController
@RequestMapping("/api/attachments")
@Tag(name = "附件管理")
@SecurityRequirement(name = "spring-security")
public interface AttachmentApi {

  /**
   * 根据条件分页查询附件
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 附件分页结果
   */
  @PreAuthorize("hasAuthority('attachment:page')")
  @Operation(operationId = "listAttachments", summary = "根据条件分页查询附件")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AttachmentPageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "originalFilename", description = "原始文件名", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "minimumSize", description = "最小文件大小(KB)", schema = @Schema(type = "integer"))
  @Parameter(in = ParameterIn.QUERY, name = "maximumSize", description = "最大文件大小(KB)", schema = @Schema(type = "integer"))
  @Parameter(in = ParameterIn.QUERY, name = "createdBy", description = "创建人ID", schema = @Schema(type = "integer"))
  @Parameter(in = ParameterIn.QUERY, name = "createdTime", description = "创建时间", schema = @Schema(type = "date-time"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<Attachment>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                      @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询附件
   *
   * @param id 附件id
   * @return 附件数据实体
   */
  @PreAuthorize("hasAuthority('attachment:load')")
  @Operation(operationId = "loadAttachment", summary = "根据id查询附件")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AttachmentResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "附件id", schema = @Schema(type = "integer"))
  @GetMapping("{id}")
  DataResponse<Attachment> load(@PathVariable("id") Long id);

  /**
   * 删除附件
   *
   * @param id 附件id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('attachment:delete')")
  @Operation(operationId = "deleteAttachment", summary = "删除附件")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "附件id", schema = @Schema(type = "integer"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") Long id);

  /**
   * 上传附件
   *
   * @param attachment 附件
   * @return 附件数据实体
   */
  @PreAuthorize("hasAuthority('attachment:upload')")
  @Operation(operationId = "uploadAttachment", summary = "上传附件")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AttachmentResult.class))}
  )
  @io.swagger.v3.oas.annotations.parameters.RequestBody(
    content = @Content(
      mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
      schema = @Schema(type = "object"),
      schemaProperties = @SchemaProperty(name = "attachment", schema = @Schema(type = "string", format = "binary"))
    ),
    required = true
  )
  @PostMapping("upload")
  DataResponse<Attachment> upload(@RequestParam("attachment") MultipartFile attachment);

  /**
   * 根据id下载附件
   *
   * @param id       附件id
   * @param response 响应
   */
  @PreAuthorize("hasAuthority('attachment:download')")
  @Operation(operationId = "loadAttachment", summary = "根据id下载附件")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AttachmentResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "附件id", schema = @Schema(type = "integer"))
  @GetMapping("{id}/download")
  void download(@PathVariable("id") Long id, HttpServletResponse response);

  class AttachmentPageResult extends DataResponse<Page<AttachmentEntity>> {

  }

  class AttachmentResult extends DataResponse<AttachmentEntity> {

  }
}
