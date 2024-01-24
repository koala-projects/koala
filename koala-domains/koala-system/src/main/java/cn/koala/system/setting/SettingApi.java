package cn.koala.system.setting;

import cn.koala.openapi.PageableAsQueryParam;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 设置接口
 *
 * @author Koala Code Gen
 */
@RestController
@RequestMapping("/api/settings")
@SecurityRequirement(name = "spring-security")
@Tag(name = "01-09 设置管理")
public interface SettingApi {

  /**
   * 根据条件分页查询设置
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 设置分页结果
   */
  @PreAuthorize("hasAuthority('setting.read')")
  @Operation(operationId = "listSetting", summary = "根据条件分页查询设置")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SettingPageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "code", description = "设置代码", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "name", description = "设置名称", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "type", description = "设置类型", schema = @Schema(type = "string"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<Setting>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                   @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询设置
   *
   * @param id 设置id
   * @return 设置数据实体
   */
  @PreAuthorize("hasAuthority('setting.read')")
  @Operation(operationId = "loadSetting", summary = "根据id查询设置")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SettingResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "设置id", schema = @Schema(type = "integer"))
  @GetMapping("{id}")
  DataResponse<Setting> load(@PathVariable("id") Long id);

  // /**
  //  * 创建设置
  //  *
  //  * @param entity 设置数据实体
  //  * @return 设置数据实体
  //  */
  // @PreAuthorize("hasAuthority('setting.create')")
  // @Operation(operationId = "createSetting", summary = "创建设置")
  // @ApiResponse(responseCode = "200", description = "成功",
  //   content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SettingResult.class))}
  // )
  // @PostMapping
  // DataResponse<SettingEntity> create(@Validated(Create.class) @RequestBody SettingEntity entity);

  /**
   * 更新设置
   *
   * @param id     设置id
   * @param entity 设置数据实体
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('setting.update')")
  @Operation(operationId = "updateSetting", summary = "更新设置")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "设置id", schema = @Schema(type = "integer"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") Long id, @Validated(Update.class) @RequestBody SettingEntity entity);

  // /**
  //  * 删除设置
  //  *
  //  * @param id 设置id
  //  * @return 操作结果
  //  */
  // @PreAuthorize("hasAuthority('setting.delete')")
  // @Operation(operationId = "deleteSetting", summary = "删除设置")
  // @ApiResponse(responseCode = "200", description = "成功",
  //   content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  // )
  // @Parameter(in = ParameterIn.PATH, name = "id", description = "设置id", schema = @Schema(type = "integer"))
  // @DeleteMapping("{id}")
  // Response delete(@PathVariable("id") Long id);

  class SettingPageResult extends DataResponse<Page<SettingEntity>> {

  }

  class SettingResult extends DataResponse<SettingEntity> {

  }
}
