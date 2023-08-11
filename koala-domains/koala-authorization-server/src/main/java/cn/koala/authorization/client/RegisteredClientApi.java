package cn.koala.authorization.client;

import cn.koala.openapi.PageableAsQueryParam;
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

import java.util.List;
import java.util.Map;

/**
 * 字典接口
 *
 * @author Houtaroy
 */
@RestController
@RequestMapping("/api/registered-clients")
@Validated
@Tag(name = "注册客户端管理")
@SecurityRequirement(name = "spring-security")
public interface RegisteredClientApi {

  /**
   * 根据条件分页查询注册客户端
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 注册客户端数据传输实体列表
   */
  @PreAuthorize("hasAuthority('registered-client:page')")
  @Operation(operationId = "listDictionaries", summary = "根据条件分页查询注册客户端")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RegisteredClientPageResult.class))}
  )
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<RegisteredClientDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                               @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询注册客户端
   *
   * @param id 注册客户端id
   * @return 注册客户端数据传输实体
   */
  @PreAuthorize("hasAuthority('registered-client:load')")
  @Operation(operationId = "loadDictionary", summary = "根据id查询注册客户端")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RegisteredClientPageResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "注册客户端id", schema = @Schema(type = "string"))
  @GetMapping("{id}")
  DataResponse<RegisteredClientDTO> load(@PathVariable("id") String id);

  /**
   * 创建注册客户端
   *
   * @param dto 注册客户端数据传输实体
   * @return 字典
   */
  @PreAuthorize("hasAuthority('registered-client:create')")
  @Operation(operationId = "createDictionary", summary = "创建注册客户端")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RegisteredClientResult.class))}
  )
  @PostMapping
  DataResponse<RegisteredClientDTO> create(@Validated(Create.class) @RequestBody RegisteredClientDTO dto);

  /**
   * 更新注册客户端
   *
   * @param id  注册客户端id
   * @param dto 注册客户端数据传输实体
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('registered-client:update')")
  @Operation(operationId = "updateDictionary", summary = "更新注册客户端")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "注册客户端id", schema = @Schema(type = "string"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") String id, @Validated(Update.class) @RequestBody RegisteredClientDTO dto);

  /**
   * 删除注册客户端
   *
   * @param id 注册客户端id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('registered-client:delete')")
  @Operation(operationId = "deleteDictionary", summary = "删除注册客户端")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "注册客户端id", schema = @Schema(type = "string"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") String id);


  class RegisteredClientPageResult extends DataResponse<List<RegisteredClientDTO>> {

  }

  class RegisteredClientResult extends DataResponse<RegisteredClientDTO> {

  }
}
