package cn.koala.template.api;

import cn.koala.openapi.PageableAsQueryParam;
import cn.koala.template.domain.Template;
import cn.koala.template.domain.TemplateEntity;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 模板接口
 *
 * @author Houtaroy
 */
@RestController
@RequestMapping("/api/templates")
@Tag(name = "05-02 模板管理")
@SecurityRequirement(name = "spring-security")
public interface TemplateApi {

  /**
   * 根据条件分页查询模板
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 模板列表
   */
  @PreAuthorize("hasAuthority('template.read')")
  @Operation(operationId = "listTemplates", summary = "根据条件分页查询模板")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TemplatePageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "name", description = "模板名称", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "groupId", description = "模板组id", schema = @Schema(type = "integer"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<Template>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                    @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询模板
   *
   * @param id 模板id
   * @return 模板
   */
  @PreAuthorize("hasAuthority('template.read')")
  @Operation(operationId = "loadTemplate", summary = "根据id查询模板")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TemplateResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "模板id", schema = @Schema(type = "integer"))
  @GetMapping("{id}")
  DataResponse<Template> load(@PathVariable("id") Long id);

  /**
   * 创建模板
   *
   * @param entity 模板数据实体
   * @return 模板
   */
  @PreAuthorize("hasAuthority('template.create')")
  @Operation(operationId = "createTemplate", summary = "创建模板")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TemplateResult.class))}
  )
  @PostMapping
  DataResponse<Template> create(@RequestBody TemplateEntity entity);

  /**
   * 更新模板
   *
   * @param id     模板id
   * @param entity 模板数据实体
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('template.update')")
  @Operation(operationId = "updateTemplate", summary = "更新模板")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "模板id", schema = @Schema(type = "integer"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") Long id, @RequestBody TemplateEntity entity);

  /**
   * 删除模板
   *
   * @param id 模板id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('template.delete')")
  @Operation(operationId = "deleteTemplate", summary = "删除模板")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "模板id", schema = @Schema(type = "integer"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") Long id);

  /**
   * 渲染模板
   *
   * @param parameters 模板参数
   * @return 渲染结果
   */
  @PreAuthorize("hasAuthority('template.render')")
  @Operation(operationId = "renderTemplate", summary = "渲染模板")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TemplatePageResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "模板id", schema = @Schema(type = "integer"))
  @PostMapping("{id}/render")
  DataResponse<String> render(@PathVariable("id") Long id, @RequestBody Map<String, Object> parameters);

  class TemplatePageResult extends DataResponse<Page<TemplateEntity>> {

  }

  class TemplateResult extends DataResponse<TemplateEntity> {

  }
}
