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
 * 数据接口
 *
 * @author Houtaroy
 */
@RequestMapping("/api/data")
@RestController
@Tag(name = "数据管理")
public interface DataApi {

  /**
   * 查询数据
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 用户列表
   */
  @Operation(summary = "查询数据", tags = {"data"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DataPageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "metadataId", description = "元数据ID", schema = @Schema(type = "string"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<Map<String, Object>>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                               @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查看数据
   *
   * @param id 数据id
   * @return 数据对象
   */
  @Operation(summary = "根据id查看数据", tags = {"data"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DataResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "数据id", schema = @Schema(type = "string"))
  @GetMapping("{id}")
  DataResponse<Map<String, Object>> load(@PathVariable("id") String id);

  /**
   * 创建数据
   *
   * @param createDataRequest 创建数据请求对象
   * @return 操作结果
   */
  @Operation(summary = "创建数据", tags = {"data"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @PostMapping
  Response create(@RequestBody CreateDataRequest createDataRequest);

  /**
   * 更新数据
   *
   * @param id   数据id
   * @param data 数据对象
   * @return 操作结果
   */
  @Operation(summary = "更新数据", tags = {"data"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "数据id", schema = @Schema(type = "string"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") String id, @RequestBody Map<String, Object> data);

  /**
   * 根据id删除数据
   *
   * @param id 数据id
   * @return 操作结果
   */
  @Operation(summary = "根据id删除数据", tags = {"data"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "数据id", schema = @Schema(type = "string"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") String id);

  class DataPageResult extends DataResponse<Page<Map<String, Object>>> {

  }

  class DataResult extends DataResponse<Map<String, Object>> {

  }
}
