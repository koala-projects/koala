package ${packageName}.apis;

import ${packageName}.entities.${domain.className}Entity;

import cn.koala.swagger.PageableAsQueryParam;
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
 * @author Koala Eucalyptus
 */
@RequestMapping("/${domain.pluralCode}")
@RestController
@SecurityRequirement(name = "spring-security")
@Tag(name = "${domain.pluralCode}", description = "${domain.name}管理")
public interface ${domain.className}Api {
  /**
   * 根据条件分页查询${domain.name}
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return ${domain.name}列表
   */
  @PreAuthorize("hasAuthority('api:${domain.pluralCode}:page')")
  @Operation(summary = "根据条件分页查询${domain.name}", tags = {"${domain.pluralCode}"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ${domain.className}PageResult.class))}
  )
  <#list searchParameters as parameter>
  @Parameter(in = ParameterIn.QUERY, name = "${parameter.code}", description = "${parameter.name}", schema = @Schema(type = "${parameter.type}"))
  </#list>
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<${domain.className}Entity>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters, @Parameter(hidden = true) Pageable pageable);
  <#if domain.idProperty??>

  /**
   * 根据id查询${domain.name}
   *
   * @param id ${domain.idProperty.name}
   * @return ${domain.name}对象
   */
  @PreAuthorize("hasAuthority('api:${domain.pluralCode}:loadById')")
  @Operation(summary = "根据id查询${domain.name}", tags = {"${domain.pluralCode}"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ${domain.className}Result.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "${domain.idProperty.name}", schema = @Schema(type = "${domain.idProperty.type}"))
  @GetMapping("{id}")
  DataResponse<${domain.className}Entity> loadById(@PathVariable("id") ${domain.idProperty.type} id);
  </#if>

  /**
   * 新增${domain.name}
   *
   * @param user ${domain.name}对象
   * @return ${domain.name}对象
   */
  @PreAuthorize("hasAuthority('api:${domain.pluralCode}:add')")
  @Operation(summary = "创建${domain.name}", tags = {"${domain.pluralCode}"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ${domain.className}Result.class))}
  )
  @PostMapping
  DataResponse<${domain.className}Entity> create(@RequestBody ${domain.className}Entity entity);

  <#if domain.idProperty??>
  /**
   * 更新${domain.name}
   *
   * @param id   ${domain.idProperty.name}
   * @param user ${domain.name}对象
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('api:${domain.pluralCode}:updateById')")
  @Operation(summary = "更新${domain.name}", tags = {"${domain.pluralCode}"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "${domain.idProperty.name}", schema = @Schema(type = "${domain.idProperty.type}"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") ${domain.idProperty.type} id, @RequestBody ${domain.className}Entity entity);

  /**
   * 删除${domain.name}
   *
   * @param id ${domain.idProperty.name}
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('api:${domain.pluralCode}:deleteById')")
  @Operation(summary = "删除${domain.name}", tags = {"${domain.pluralCode}"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "${domain.idProperty.name}", schema = @Schema(type = "${domain.idProperty.type}"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") ${domain.idProperty.type} id);
  </#if>

  class ${domain.className}PageResult extends DataResponse<Page<${domain.className}Entity>> {

  }

  class ${domain.className}Result extends DataResponse<${domain.className}Entity> {

  }
}
