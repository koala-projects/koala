package cn.koala.datamodel;

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

import java.util.Map;

/**
 * 元数据接口
 *
 * @author Houtaroy
 */
@RequestMapping("/api/metadata")
@RestController
@Tag(name = "元数据管理")
public interface MetadataApi {

  /**
   * 查询元数据
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 用户列表
   */
  @Operation(summary = "查询元数据", tags = {"metadata"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MetadataPageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "code", description = "元数据代码", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "name", description = "元数据名称", schema = @Schema(type = "string"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<Metadata>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                    @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查看元数据
   *
   * @param id 元数据id
   * @return 元数据
   */
  @Operation(summary = "根据id查看元数据", tags = {"metadata"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MetadataResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "元数据id", schema = @Schema(type = "string"))
  @GetMapping("{id}")
  DataResponse<Metadata> load(@PathVariable("id") String id);

  /**
   * 创建元数据
   *
   * @param metadata 元数据对象
   * @return 已创建的元数据对象
   */
  @Operation(summary = "创建元数据", tags = {"metadata"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MetadataResult.class))}
  )
  @PostMapping
  DataResponse<Metadata> create(@RequestBody MetadataEntity metadata);

  /**
   * 更新元数据
   *
   * @param id     元数据id
   * @param entity 元数据实体
   * @return 操作结果
   */
  @Operation(summary = "更新元数据")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "元数据id", schema = @Schema(type = "string"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") String id, @RequestBody MetadataEntity entity);

  /**
   * 根据id删除元数据
   *
   * @param id 元数据id
   * @return 操作结果
   */
  @Operation(summary = "根据id删除元数据", tags = {"metadata"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "元数据id", schema = @Schema(type = "string"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") String id);

  class MetadataPageResult extends DataResponse<Page<MetadataEntity>> {

  }

  class MetadataResult extends DataResponse<MetadataEntity> {

  }
}
