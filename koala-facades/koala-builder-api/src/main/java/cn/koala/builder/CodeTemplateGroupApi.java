package cn.koala.builder;

import cn.koala.swagger.PageableAsQueryParam;
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

import java.util.List;
import java.util.Map;

/**
 * 代码模板组接口
 *
 * @author Houtaroy
 */
@RequestMapping("/api/code-template-groups")
@RestController
@Tag(name = "代码模板组管理")
public interface CodeTemplateGroupApi {

  /**
   * 分页查询代码模板组
   *
   * @param parameters 查询参数
   * @param pageable   分页参数
   * @return 代码模板组列表
   */
  @Operation(summary = "分页查询代码模板组", tags = {"codeTemplateGroup"})
  @ApiResponse(
    responseCode = "200", description = "成功",
    content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = CodeTemplateGroupPageResult.class))
    }
  )
  @Parameter(in = ParameterIn.QUERY, name = "name", description = "代码模板组名称, 模糊查询",
    schema = @Schema(type = "string"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<CodeTemplateGroup>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                             @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查看代码模板组
   *
   * @param id 代码模板组id
   * @return 代码模板组对象
   */
  @Operation(summary = "根据id查看代码模板组", tags = {"codeTemplateGroup"})
  @ApiResponse(
    responseCode = "200", description = "成功",
    content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = CodeTemplateGroupResult.class))
    }
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "代码模板组id", schema = @Schema(type = "string"))
  @GetMapping("{id}")
  DataResponse<CodeTemplateGroup> loadById(@PathVariable("id") String id);

  /**
   * 创建代码模板组
   *
   * @param entity 代码模板组对象
   * @return 已创建的代码模板组对象
   */
  @Operation(summary = "创建代码模板组", tags = {"codeTemplateGroup"})
  @ApiResponse(
    responseCode = "200", description = "成功",
    content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = CodeTemplateGroupResult.class))
    }
  )
  @PostMapping
  DataResponse<CodeTemplateGroup> create(@RequestBody CodeTemplateGroupEntity entity);

  /**
   * 更新代码模板组
   *
   * @param id     代码模板组id
   * @param entity 代码模板组对象
   * @return 操作结果
   */
  @Operation(summary = "更新代码模板组", tags = {"codeTemplateGroup"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "代码模板组id", schema = @Schema(type = "string"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") String id, @RequestBody CodeTemplateGroupEntity entity);

  /**
   * 根据id删除代码模板组
   *
   * @param id 代码模板组id
   * @return 操作结果
   */
  @Operation(summary = "根据id删除代码模板组", tags = {"codeTemplateGroup"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "代码模板组id", schema = @Schema(type = "string"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") String id);

  /**
   * 构建
   *
   * @param id   代码模板组id
   * @param body 构建请求
   * @return 构建结果
   */
  @Operation(summary = "构建", tags = {"codeTemplateGroup"})
  @ApiResponse(responseCode = "200", description = "成功", content = {
    @Content(
      mediaType = "application/json",
      schema = @Schema(implementation = CodeTemplateGroupBuildResult.class)
    )
  })
  @PostMapping("{id}/build")
  DataResponse<List<CodeBuildResult>> build(@PathVariable("id") String id, @RequestBody BuildRequest body);

  class CodeTemplateGroupPageResult extends DataResponse<Page<CodeTemplateGroup>> {

  }

  class CodeTemplateGroupResult extends DataResponse<CodeTemplateGroup> {

  }

  class CodeTemplateGroupBuildResult extends DataResponse<List<CodeBuildResult>> {

  }

  class CodeTemplateGroupBuildDownloadResult extends DataResponse<BuildDownloadResponse> {

  }
}
