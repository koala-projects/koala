<#assign domainClassName = domain.code?cap_first>
package ${packageName}.apis;

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

import ${packageName}.entities.${domainClassName}Entity;

import java.util.Map;

/**
 * @author Koala Eucalyptus
 */
@RequestMapping("/${domain.pluralCode}")
@RestController
@SecurityRequirement(name = "spring-security")
@Tag(name = "${domain.pluralCode}", description = "${domain.name}管理")
public interface ${domainClassName}Api {
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
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ${domainClassName}PageResult.class))}
  )
  <#list searchParameters as parameter>
  @Parameter(in = ParameterIn.QUERY, name = "${parameter.code}", description = "${parameter.name}", schema = @Schema(type = "${parameter.type}"))
  </#list>
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<${domainClassName}Entity>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters, @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询${domain.name}
   *
   * @param id ${domain.name}id
   * @return ${domain.name}对象
   */
  @PreAuthorize("hasAuthority('api:${domain.pluralCode}:loadById')")
  @Operation(summary = "根据id查询${domain.name}", tags = {"${domain.pluralCode}"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ${domainClassName}Result.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "${domain.name}id", schema = @Schema(type = "string"))
  @GetMapping("{id}")
  DataResponse<${domainClassName}Entity> loadById(@PathVariable("id") String id);

  /**
   * 新增${domain.name}
   *
   * @param user ${domain.name}对象
   * @return ${domain.name}对象
   */
  @PreAuthorize("hasAuthority('api:${domain.pluralCode}:add')")
  @Operation(summary = "创建${domain.name}", tags = {"${domain.pluralCode}"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ${domainClassName}Result.class))}
  )
  @PostMapping
  DataResponse<${domainClassName}Entity> create(@RequestBody ${domainClassName}Entity entity);

  /**
   * 更新${domain.name}
   *
   * @param id   ${domain.name}id
   * @param user ${domain.name}对象
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('api:${domain.pluralCode}:updateById')")
  @Operation(summary = "更新${domain.name}", tags = {"${domain.pluralCode}"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "${domain.name}id", schema = @Schema(type = "string"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") String id, @RequestBody ${domainClassName}Entity entity);

  /**
   * 删除${domain.name}
   *
   * @param id ${domain.name}id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('api:${domain.pluralCode}:deleteById')")
  @Operation(summary = "删除${domain.name}", tags = {"${domain.pluralCode}"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "${domain.name}id", schema = @Schema(type = "string"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") String id);

  class ${domainClassName}PageResult extends DataResponse<Page<${domainClassName}Entity>> {

  }

  class ${domainClassName}Result extends DataResponse<${domainClassName}Entity> {

  }
}
