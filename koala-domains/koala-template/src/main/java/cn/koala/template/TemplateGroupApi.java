package cn.koala.template;

import cn.koala.openapi.PageableAsQueryParam;
import cn.koala.template.support.TemplateGroupEntity;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
 * 模板组接口
 *
 * @author Houtaroy
 */
@RestController
@RequestMapping("/api/template-groups")
@Tag(name = "模板组管理")
public interface TemplateGroupApi {

  /**
   * 根据条件分页查询模板组
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 模板组列表
   */
  @Operation(operationId = "listTemplateGroups", summary = "根据条件分页查询模板组")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TemplateGroupPageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "name", description = "模板组名称", schema = @Schema(type = "string"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<TemplateGroup>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                         @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询模板组
   *
   * @param id 模板组id
   * @return 模板组
   */
  @Operation(operationId = "loadTemplateGroup", summary = "根据id查询模板组")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TemplateGroupResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "模板组id", schema = @Schema(type = "string"))
  @GetMapping("{id}")
  DataResponse<TemplateGroup> load(@PathVariable("id") Long id);

  /**
   * 创建模板组
   *
   * @param entity 模板组数据实体
   * @return 模板组
   */
  @Operation(operationId = "createTemplateGroup", summary = "创建模板组")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TemplateGroupResult.class))}
  )
  @PostMapping
  DataResponse<TemplateGroup> create(@RequestBody TemplateGroupEntity entity);

  /**
   * 更新模板组
   *
   * @param id     模板组id
   * @param entity 模板组数据实体
   * @return 操作结果
   */
  @Operation(operationId = "updateTemplateGroup", summary = "更新模板组")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "模板组id", schema = @Schema(type = "integer"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") Long id, @RequestBody TemplateGroupEntity entity);

  /**
   * 删除模板组
   *
   * @param id 模板组id
   * @return 操作结果
   */
  @Operation(operationId = "deleteTemplateGroup", summary = "删除模板组")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "模板组id", schema = @Schema(type = "string"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") Long id);

  class TemplateGroupPageResult extends DataResponse<Page<TemplateGroupEntity>> {

  }

  class TemplateGroupResult extends DataResponse<TemplateGroupEntity> {

  }
}
