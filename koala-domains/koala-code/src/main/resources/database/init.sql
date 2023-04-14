insert into t_database(name, url, username, password, catalog, `schema`, is_systemic)
values ('演示数据库', 'jdbc:mysql://120.46.222.245:3306/koala_demo', 'koala_demo', 'koala_demo',
        'koala_demo', 'koala_demo', 1);

insert into t_template_group(id, name, remark, is_systemic)
values (999, '考拉代码', '考拉代码生成模板', 1);

insert into t_template(name, remark, content, group_id, is_systemic)
values ('apis/Api.java', '接口代码模板', 'package #(package).apis;

import #(package).entities.#(name)Entity;

import cn.koala.openapi.PageableAsQueryParam;
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
 * #(description)接口
 *
 * @author Koala Code Generator
 */
@RestController
@RequestMapping("/api/#(api)")
@Tag(name = "#(description)")
@SecurityRequirement(name = "spring-security")
public interface #(name)Api {

  /**
   * 根据条件分页查询#(description)
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return #(description)分页结果
   */
  @PreAuthorize("hasAuthority(''#(permission):page'')")
  @Operation(operationId = "list#(pluralName)", summary = "根据条件分页查询#(description)")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = #(name)PageResult.class))}
  )
#for(parameter: parameters)
  @Parameter(in = ParameterIn.QUERY, name = "#(parameter.name)", description = "#(parameter.description)", schema = @Schema(type = "#(parameter.jsonType)"))
#end
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<#(name)Entity>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                         @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询#(description)
   *
   * @param id #(description)id
   * @return #(description)数据实体
   */
  @PreAuthorize("hasAuthority(''#(permission):load'')")
  @Operation(operationId = "load#(name)", summary = "根据id查询#(description)")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = #(name)Result.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "#(description)id", schema = @Schema(type = "#(id.jsonType)"))
  @GetMapping("{id}")
  DataResponse<#(name)Entity> load(@PathVariable("id") #(id.javaType) id);

  /**
   * 创建#(description)
   *
   * @param entity #(description)数据实体
   * @return #(description)数据实体
   */
  @PreAuthorize("hasAuthority(''#(permission):create'')")
  @Operation(operationId = "create#(name)", summary = "创建数据实体")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = #(name)Result.class))}
  )
  @PostMapping
  DataResponse<#(name)Entity> add(@RequestBody #(name)Entity entity);

  /**
   * 更新#(description)
   *
   * @param id     #(description)id
   * @param entity #(description)数据实体
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority(''#(permission):update'')")
  @Operation(operationId = "update#(name)", summary = "更新#(description)")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "#(description)id", schema = @Schema(type = "#(id.jsonType)"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") #(id.javaType) id, @RequestBody #(name)Entity entity);

  /**
   * 删除#(description)
   *
   * @param id #(description)id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority(''#(permission):delete'')")
  @Operation(operationId = "delete#(name)", summary = "删除#(description)")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "#(description)id", schema = @Schema(type = "#(id.jsonType)"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") #(id.javaType) id);

  class #(name)PageResult extends DataResponse<Page<#(name)Entity>> {

  }

  class #(name)Result extends DataResponse<#(name)Entity> {

  }
}
', 999, 1),
       ('apis/ApiImpl.java', '接口实现类代码模板', 'package #(package).apis;

import #(package).entities.#(name)Entity;
import #(package).services.#(name)Service;

import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * #(description)接口实现类
 *
 * @author Koala Code Generator
 */
@RequiredArgsConstructor
@RestController
public class #(name)ApiImpl implements #(name)Api {

  protected final #(name)Service service;

  @Override
  public DataResponse<Page<#(name)Entity>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.page(parameters, pageable));
  }

  @Override
  public DataResponse<#(name)Entity> load(#(id.javaType) id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  public DataResponse<#(name)Entity> create(#(name)Entity entity) {
    service.add(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(#(id.javaType) id, #(name)Entity entity) {
    entity.setIdIfAbsent(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(#(id.javaType) id) {
    service.delete(#(name)Entity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
', 999, 1),
       ('entities/Entity.java', '数据实体类代码模板', 'package #(package).entities;

#if(implements.contains(''Auditable<Long>''))
import cn.koala.persist.domain.Auditable;
#end
import cn.koala.persist.domain.Persistable;
#if(implements.contains(''Sortable''))
import cn.koala.persist.domain.Sortable;
#end
#if(implements.contains(''Stateful''))
import cn.koala.persist.domain.Stateful;
import cn.koala.persist.domain.YesNo;
#end
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * #(description)数据实体类
 *
 * @author Koala Code Generator
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "#(description)数据实体类")
public class #(name)Entity implements Persistable<#(id.javaType)>#for(implement: implements), #(implement)#end  {
#for(property: properties)
  @Schema(description = "#(property.description)")
  private #(property.javaType) #(property.name);
#end
}
', 999, 1),
       ('services/Service.java', '服务类代码模板', 'package #(package).services;

import #(package).#(name)Entity;
import #(package).#(name)Repository;

import cn.koala.mybatis.BaseMyBatisService;

/**
 * #(description)服务类
 *
 * @author Koala Code Generator
 */
public class #(name)Service extends BaseMyBatisService<#(name)Entity, #(id.javaType)> {
  /**
   * 构造函数
   *
   * @param repository 仓库接口
   */
  public #(name)Service(#(name)Repository repository) {
    super(repository);
  }
}
', 999, 1),
       ('repositories/Repository.java', '仓库接口代码模板', 'package #(package).repositories;

import #(package).#(name)Entity;

import cn.koala.persist.CrudRepository;

/**
 * #(description)仓库接口
 *
 * @author Koala Code Generator
 */
public interface #(name)Repository extends CrudRepository<#(name)Entity, #(id.javaType)> {
}
', 999, 1),
       ('mappers/Mapper.xml', 'Mapper文件代码模板', '<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="#(package).repositories.#(name)Repository">

  <sql id="select#(name)">
    select
#for(column : columns)
    t.#(column.name)#if(!for.last),#end
#end
    from #(table.name) t
  </sql>

  <select id="find" resultType="#(package).entities.#(name)Entity">
    <include refid="select#(name)"/>
    <where>
#if(implements.contains(''Stateful''))
      t.is_deleted = ${@cn.koala.persist.domain.YesNo@NO.value}
#end
#for(i = 0; i < columns.size(); i++)
      <if test="#(properties[i].name) != null and #(properties[i].name) != ''''">
       #if(implements.contains(''Stateful'')) and#end  t.#(columns[i].name) = #{#(properties[i].name)}
      </if>
#end
    </where>
  </select>

  <select id="findById" resultType="#(package).entities.#(name)Entity">
    <include refid="select#(name)"/>
    where#if(implements.contains(''Stateful'')) t.is_deleted = ${@cn.koala.persist.domain.YesNo@NO.value} and#end  t.id=#{id}
  </select>

  <insert id="add" parameterType="#(package).entities.#(name)Entity">
    insert into #(table.name)
	value (
#for(property : properties)
    #{#(property.name)}#if(!for.last),#end
#end
    )
  </insert>

  <update id="update" parameterType="#(package).entities.#(name)Entity">
    update #(table.name)
    <trim prefix="set" suffixOverrides=",">
#for(i = 0; i < columns.size(); i++)
      <if test="#(properties[i].name) != null">#(columns[i].name)=#{#(properties[i].name)},</if>
#end
    </trim>
    where#if(implements.contains(''Stateful'')) is_deleted = ${@cn.koala.persist.domain.YesNo@NO.value} and#end  id=#{id}
  </update>

#if(implements.contains(''Stateful''))
  <update id="delete" parameterType="#(package).entities.#(name)Entity">
    update #(table.name)
    set is_deleted   = ${@cn.koala.persist.domain.YesNo@YES.value}#if(implements.contains(''Auditable<Long>'')),#end
#if(implements.contains(''Auditable<Long>''))
        deleted_by   = #{deletedBy},
        deleted_time = #{deletedTime}
#end
    where id = #{id}
  </update>
#else
  <delete id="delete" parameterType="#(package).entities.#(name)Entity">
    delete from #(table.name) where id = #{id}
  </delete>
#end
</mapper>
', 999, 1);