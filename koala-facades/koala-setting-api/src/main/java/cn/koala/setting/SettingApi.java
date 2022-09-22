package cn.koala.setting;

import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 设置接口
 *
 * @author Houtaroy
 */
@RequestMapping("/api/settings")
@RestController
@Tag(name = "setting", description = "设置接口")
public interface SettingApi {
  /**
   * 根据id查看设置
   *
   * @param id 设置id
   * @return 设置对象
   */
  @Operation(summary = "根据id查看设置", tags = {"setting"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SettingResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "设置id", schema = @Schema(type = "string"))
  @GetMapping("{id}")
  DataResponse<Map<String, Object>> loadById(@PathVariable("id") String id);

  /**
   * 更新设置
   *
   * @param id       设置id
   * @param settings 设置内容
   * @return 操作结果
   */
  @Operation(summary = "更新设置", tags = {"setting"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "设置id", schema = @Schema(type = "string"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") String id, @RequestBody Map<String, Object> settings);

  class SettingResult extends DataResponse<Map<String, Object>> {

  }
}
