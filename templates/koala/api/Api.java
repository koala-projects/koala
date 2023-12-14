package #(package).api;

import #(package).entity.#(name.pascal.singular)Entity;
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

import java.util.Map;

/**
 * #(description)接口
 *
 * @author Koala Code Gen
 */
@RestController
@RequestMapping("/api/#(name.kebab.plural)")
@SecurityRequirement(name = "spring-security")
@Tag(name = "#(description)")
public interface #(name.pascal.singular)Api {

  /**
   * 根据条件分页查询#(description)
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return #(description)分页结果
   */
  @PreAuthorize("hasAuthority('#(name.kebab.singular).read')")
  @Operation(operationId = "list#(name.pascal.singular)", summary = "根据条件分页查询#(description)")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = #(name.pascal.singular)PageResult.class))}
  )
#for(parameter: koala.parameters)
  @Parameter(in = ParameterIn.QUERY, name = "#(parameter.name)", description = "#(parameter.description)", schema = @Schema(type = "#(parameter.type)"))
#end
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<#(name.pascal.singular)Entity>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters, 
														 @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询#(description)
   *
   * @param id #(description)id
   * @return #(description)数据实体
   */
  @PreAuthorize("hasAuthority('#(name.kebab.singular).read')")
  @Operation(operationId = "load#(name.pascal.singular)", summary = "根据id查询#(description)")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = #(name.pascal.singular)Result.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "#(description)id", schema = @Schema(type = "#(id.type.json)"))
  @GetMapping("{id}")
  DataResponse<#(name.pascal.singular)Entity> load(@PathVariable("id") #(id.type.java) id);

  /**
   * 创建#(description)
   *
   * @param entity #(description)数据实体
   * @return #(description)数据实体
   */
  @PreAuthorize("hasAuthority('#(name.kebab.singular).create')")
  @Operation(operationId = "create#(name.pascal.singular)", summary = "创建#(description)")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = #(name.pascal.singular)Result.class))}
  )
  @PostMapping
  DataResponse<#(name.pascal.singular)Entity> create(@Validated(Create.class) @RequestBody #(name.pascal.singular)Entity entity);

  /**
   * 更新#(description)
   *
   * @param id     #(description)id
   * @param entity #(description)数据实体
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('#(name.kebab.singular).update')")
  @Operation(operationId = "update#(name.pascal.singular)", summary = "更新#(description)")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "#(description)id", schema = @Schema(type = "#(id.type.json)"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") #(id.type.java) id, @Validated(Update.class) @RequestBody #(name.pascal.singular)Entity entity);

  /**
   * 删除#(description)
   *
   * @param id #(description)id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('#(name.kebab.singular).delete')")
  @Operation(operationId = "delete#(name.pascal.singular)", summary = "删除#(description)")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "#(description)id", schema = @Schema(type = "#(id.type.json)"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") #(id.type.java) id);

  class #(name.pascal.singular)PageResult extends DataResponse<Page<#(name.pascal.singular)Entity>> {

  }

  class #(name.pascal.singular)Result extends DataResponse<#(name.pascal.singular)Entity> {

  }
}
