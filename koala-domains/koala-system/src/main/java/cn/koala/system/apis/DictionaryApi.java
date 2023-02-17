package cn.koala.system.apis;

import cn.koala.openapi.PageableAsQueryParam;
import cn.koala.system.Dictionary;
import cn.koala.system.entities.DictionaryEntity;
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
 * 字典接口
 *
 * @author Houtaroy
 */
@RestController
@RequestMapping("/api/dictionaries")
@Tag(name = "字典管理")
@SecurityRequirement(name = "spring-security")
public interface DictionaryApi {

  /**
   * 根据条件分页查询字典
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 字典列表
   */
  @PreAuthorize("hasAuthority('system:dictionary:page')")
  @Operation(summary = "根据条件分页查询字典")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DictionaryPageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "code", description = "字典代码", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "name", description = "字典名称", schema = @Schema(type = "string"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<Dictionary>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                      @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询字典
   *
   * @param id 字典id
   * @return 字典
   */
  @PreAuthorize("hasAuthority('system:dictionary:load')")
  @Operation(summary = "根据id查询字典")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DictionaryResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "字典id", schema = @Schema(type = "string"))
  @GetMapping("{id}")
  DataResponse<Dictionary> load(@PathVariable("id") Long id);

  /**
   * 创建字典
   *
   * @param entity 字典数据实体
   * @return 字典
   */
  @PreAuthorize("hasAuthority('system:dictionary:create')")
  @Operation(summary = "创建字典")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DictionaryResult.class))}
  )
  @PostMapping
  DataResponse<Dictionary> add(@RequestBody DictionaryEntity entity);

  /**
   * 更新字典
   *
   * @param id     字典id
   * @param entity 字典数据实体
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('system:dictionary:update')")
  @Operation(summary = "更新字典")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "字典id", schema = @Schema(type = "string"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") Long id, @RequestBody DictionaryEntity entity);

  /**
   * 删除字典
   *
   * @param id 字典id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('system:dictionary:delete')")
  @Operation(summary = "删除字典")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "字典id", schema = @Schema(type = "string"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") Long id);

  class DictionaryPageResult extends DataResponse<Page<DictionaryEntity>> {

  }

  class DictionaryResult extends DataResponse<DictionaryEntity> {

  }
}
