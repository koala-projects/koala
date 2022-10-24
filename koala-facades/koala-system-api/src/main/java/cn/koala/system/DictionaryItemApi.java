package cn.koala.system;

import cn.koala.swagger.PageableAsQueryParam;
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
 * 字典项接口
 *
 * @author Houtaroy
 */
@RestController
@RequestMapping("/api/dictionary-items")
@Tag(name = "字典项接口")
@SecurityRequirement(name = "spring-security")
public interface DictionaryItemApi {

  /**
   * 根据条件分页查询字典项
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 字典项列表
   */
  @PreAuthorize("hasAuthority('dictionary:read')")
  @Operation(summary = "根据条件分页查询字典项")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(
      mediaType = "application/json",
      schema = @Schema(implementation = DictionaryItemPageResult.class)
    )}
  )
  @Parameter(in = ParameterIn.QUERY, name = "name", description = "字典项名称", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "dictionaryId", description = "字典ID", schema = @Schema(type = "string"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<DictionaryItem>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                          @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询字典项
   *
   * @param id 字典项id
   * @return 字典项
   */
  @PreAuthorize("hasAuthority('dictionary:read')")
  @Operation(summary = "根据id查询字典项")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DictionaryItemResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "字典项id", schema = @Schema(type = "string"))
  @GetMapping("{id}")
  DataResponse<DictionaryItem> loadById(@PathVariable("id") String id);

  /**
   * 创建字典项
   *
   * @param entity 字典项数据实体
   * @return 字典项
   */
  @PreAuthorize("hasAuthority('dictionary:write')")
  @Operation(summary = "创建字典项")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DictionaryItemResult.class))}
  )
  @PostMapping
  DataResponse<DictionaryItem> create(@RequestBody DictionaryItemEntity entity);

  /**
   * 更新字典项
   *
   * @param id     字典项id
   * @param entity 字典项数据实体
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('dictionary:write')")
  @Operation(summary = "更新字典项")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "字典项id", schema = @Schema(type = "string"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") String id, @RequestBody DictionaryItemEntity entity);

  /**
   * 删除字典项
   *
   * @param id 字典项id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('dictionary:write')")
  @Operation(summary = "删除字典项")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "字典项id", schema = @Schema(type = "string"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") String id);

  class DictionaryItemPageResult extends DataResponse<Page<DictionaryItemEntity>> {

  }

  class DictionaryItemResult extends DataResponse<DictionaryItemEntity> {

  }
}
