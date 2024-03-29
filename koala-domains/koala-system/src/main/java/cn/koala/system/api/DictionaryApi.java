package cn.koala.system.api;

import cn.koala.openapi.PageableAsQueryParam;
import cn.koala.persist.validator.EditableId;
import cn.koala.system.model.Dictionary;
import cn.koala.system.model.DictionaryEntity;
import cn.koala.validation.group.Create;
import cn.koala.validation.group.Update;
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
import org.springframework.validation.annotation.Validated;
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
@Validated
@Tag(name = "01-06 字典管理")
@SecurityRequirement(name = "spring-security")
public interface DictionaryApi {

  /**
   * 根据条件分页查询字典
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 字典列表
   */
  @PreAuthorize("hasAuthority('dictionary.read')")
  @Operation(operationId = "listDictionaries", summary = "根据条件分页查询字典")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DictionaryPageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "codeLike", description = "字典代码", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "nameLike", description = "字典名称", schema = @Schema(type = "string"))
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
  @PreAuthorize("hasAuthority('dictionary.read')")
  @Operation(operationId = "loadDictionary", summary = "根据id查询字典")
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
  @PreAuthorize("hasAuthority('dictionary.create')")
  @Operation(operationId = "createDictionary", summary = "创建字典")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DictionaryResult.class))}
  )
  @PostMapping
  DataResponse<Dictionary> create(@Validated(Create.class) @RequestBody DictionaryEntity entity);

  /**
   * 更新字典
   *
   * @param id     字典id
   * @param entity 字典数据实体
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('dictionary.update')")
  @Operation(operationId = "updateDictionary", summary = "更新字典")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "字典id", schema = @Schema(type = "string"))
  @PutMapping("{id}")
  Response update(@EditableId(Dictionary.class) @PathVariable("id") Long id,
                  @Validated(Update.class) @RequestBody DictionaryEntity entity);

  /**
   * 删除字典
   *
   * @param id 字典id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('dictionary.delete')")
  @Operation(operationId = "deleteDictionary", summary = "删除字典")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "字典id", schema = @Schema(type = "string"))
  @DeleteMapping("{id}")
  Response delete(@EditableId(Dictionary.class) @PathVariable("id") Long id);

  class DictionaryPageResult extends DataResponse<Page<DictionaryEntity>> {

  }

  class DictionaryResult extends DataResponse<DictionaryEntity> {

  }
}
