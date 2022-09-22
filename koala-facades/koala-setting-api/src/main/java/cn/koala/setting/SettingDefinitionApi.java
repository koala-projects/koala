package cn.koala.setting;

import cn.koala.datamodel.Metadata;
import cn.koala.datamodel.PersistentMetadata;
import cn.koala.web.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 设置定义接口
 *
 * @author Houtaroy
 */
@RequestMapping("/api/setting-definitions")
@RestController
@Tag(name = "settingDefinition", description = "设置定义接口")
public interface SettingDefinitionApi {
  /**
   * 查询设置定义
   *
   * @param parameters 查询条件
   * @return 设置定义列表
   */
  @Operation(summary = "查询设置定义", tags = {"settingDefinition"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = SettingDefinitionListResult.class))
    }
  )
  @GetMapping
  DataResponse<List<Metadata>> list(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters);

  class SettingDefinitionListResult extends DataResponse<List<PersistentMetadata>> {

  }
}
