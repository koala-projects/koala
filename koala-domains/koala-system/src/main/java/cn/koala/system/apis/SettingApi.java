package cn.koala.system.apis;

import cn.koala.system.Setting;
import cn.koala.system.entities.SettingEntity;
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

import java.util.List;
import java.util.Map;

/**
 * 设置接口
 *
 * @author Houtaroy
 */
@RequestMapping("/api/settings")
@RestController
@SecurityRequirement(name = "spring-security")
@Tag(name = "设置管理")
public interface SettingApi {

  /**
   * 查询全部设置
   *
   * @return 全部设置列表
   */
  @Operation(summary = "查询全部设置")
  @Parameter(in = ParameterIn.QUERY, name = "code", description = "设置代码", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "name", description = "设置名称", schema = @Schema(type = "string"))
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SettingListResult.class))}
  )
  @GetMapping
  DataResponse<List<Setting>> list(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters);

  /**
   * 根据id查询设置
   *
   * @param id 设置id
   * @return 设置
   */
  @Operation(summary = "根据id查询设置")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SettingResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "设置id", schema = @Schema(type = "integer"))
  @GetMapping("{id}")
  DataResponse<Setting> load(@PathVariable("id") Long id);

  /**
   * 创建设置
   *
   * @param entity 设置数据实体
   * @return 设置
   */
  @PreAuthorize("hasAuthority('system:setting:create')")
  @Operation(summary = "创建设置")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SettingResult.class))}
  )
  @PostMapping
  DataResponse<Setting> create(@RequestBody SettingEntity entity);

  /**
   * 更新设置
   *
   * @param id     设置id
   * @param entity 设置数据实体
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('system:setting:update')")
  @Operation(summary = "更新设置")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "设置id", schema = @Schema(type = "integer"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") Long id, @RequestBody SettingEntity entity);

  /**
   * 删除设置
   *
   * @param id 设置id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('system:setting:delete')")
  @Operation(summary = "删除设置")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "设置id", schema = @Schema(type = "integer"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") Long id);

  class SettingResult extends DataResponse<SettingEntity> {

  }

  class SettingListResult extends DataResponse<List<Setting>> {

  }
}
