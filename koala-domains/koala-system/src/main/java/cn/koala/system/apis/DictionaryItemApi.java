package cn.koala.system.apis;

import cn.koala.openapi.PageableAsQueryParam;
import cn.koala.persist.validator.EditableId;
import cn.koala.system.DictionaryItem;
import cn.koala.system.entities.DictionaryItemEntity;
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
@RequestMapping("/api/dictionary-items")
@Validated
@Tag(name = "01-06 字典项管理")
@SecurityRequirement(name = "spring-security")
public interface DictionaryItemApi {

  /**
   * 根据条件分页查询字典项
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 字典项列表
   */
  @PreAuthorize("hasAuthority('dictionary.update')")
  @Operation(operationId = "listDictionaryItems", summary = "根据条件分页查询字典项")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DictionaryItemPageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "code", description = "字典项代码", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "name", description = "字典项名称", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "dictionaryId", description = "字典id", schema = @Schema(type = "integer"))
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
  @PreAuthorize("hasAuthority('dictionary.update')")
  @Operation(operationId = "loadDictionaryItem", summary = "根据id查询字典项")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DictionaryItemResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "字典项id", schema = @Schema(type = "integer"))
  @GetMapping("{id}")
  DataResponse<DictionaryItem> load(@PathVariable("id") Long id);

  /**
   * 创建字典项
   *
   * @param entity 字典项数据实体
   * @return 字典
   */
  @PreAuthorize("hasAuthority('dictionary.update')")
  @Operation(operationId = "createDictionaryItem", summary = "创建字典项")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DictionaryItemResult.class))}
  )
  @PostMapping
  DataResponse<DictionaryItem> create(@Validated(Create.class) @RequestBody DictionaryItemEntity entity);

  /**
   * 更新字典
   *
   * @param id     字典id
   * @param entity 字典数据实体
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('dictionary.update')")
  @Operation(operationId = "updateDictionaryItem", summary = "更新字典")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "字典项id", schema = @Schema(type = "integer"))
  @PutMapping("{id}")
  Response update(@EditableId(DictionaryItem.class) @PathVariable("id") Long id,
                  @Validated(Update.class) @RequestBody DictionaryItemEntity entity);

  /**
   * 删除字典
   *
   * @param id 字典id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('dictionary.update')")
  @Operation(operationId = "deleteDictionaryItem", summary = "删除字典")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "字典项id", schema = @Schema(type = "integer"))
  @DeleteMapping("{id}")
  Response delete(@EditableId(DictionaryItem.class) @PathVariable("id") Long id);

  class DictionaryItemPageResult extends DataResponse<Page<DictionaryItemEntity>> {

  }

  class DictionaryItemResult extends DataResponse<DictionaryItemEntity> {

  }
}
