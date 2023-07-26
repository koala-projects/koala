-- 演示数据库
insert into t_database(id, name, url, username, password, catalog, `schema`, is_systemic)
values (1, '演示数据库', 'jdbc:mysql://127.0.0.1:3306/koala_demo', 'koala_demo', 'koala_demo',
        'koala_demo', 'koala_demo', 1);

-- 考拉代码模板
insert into t_template_group(id, name, remark, is_systemic)
values (1, '考拉代码', '考拉代码生成模板', 1);

insert into t_template(id, name, remark, content, group_id, is_systemic)
values (1, 'api/Api.java', '接口代码模板', 'package #(package).api;

import #(package).entity.#(name)Entity;

import cn.koala.openapi.PageableAsQueryParam;
import cn.koala.persist.validator.EditableId;
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
 * @author Koala Code Generator
 */
@RestController
@RequestMapping("/api/#(api.path)")
@SecurityRequirement(name = "spring-security")
@Tag(name = "#(description)")
public interface #(name)Api {

  /**
   * 根据条件分页查询#(description)
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return #(description)分页结果
   */
  @PreAuthorize("hasAuthority(''#(api.permission):page'')")
  @Operation(operationId = "list#(pluralName)", summary = "根据条件分页查询#(description)")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = #(name)PageResult.class))}
  )
#for(parameter: api.parameters.others)
  @Parameter(in = ParameterIn.QUERY, name = "#(parameter.name)", description = "#(parameter.description)", schema = @Schema(type = "#(parameter.type)"))
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
  @PreAuthorize("hasAuthority(''#(api.permission):load'')")
  @Operation(operationId = "load#(name)", summary = "根据id查询#(description)")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = #(name)Result.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "#(description)id", schema = @Schema(type = "#(api.parameters.id.type)"))
  @GetMapping("{id}")
  DataResponse<#(name)Entity> load(@PathVariable("id") #(entity.properties.id.type) id);

  /**
   * 创建#(description)
   *
   * @param entity #(description)数据实体
   * @return #(description)数据实体
   */
  @PreAuthorize("hasAuthority(''#(api.permission):create'')")
  @Operation(operationId = "create#(name)", summary = "创建#(description)")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = #(name)Result.class))}
  )
  @PostMapping
  DataResponse<#(name)Entity> create(@Validated(Create.class) @RequestBody #(name)Entity entity);

  /**
   * 更新#(description)
   *
   * @param id     #(description)id
   * @param entity #(description)数据实体
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority(''#(api.permission):update'')")
  @Operation(operationId = "update#(name)", summary = "更新#(description)")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "#(description)id", schema = @Schema(type = "#(api.parameters.id.type)"))
  @PutMapping("{id}")
  Response update(@EditableId(#(name)Entity.class) @PathVariable("id") #(entity.properties.id.type) id,
                  @Validated(Update.class) @RequestBody #(name)Entity entity);

  /**
   * 删除#(description)
   *
   * @param id #(description)id
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority(''#(api.permission):delete'')")
  @Operation(operationId = "delete#(name)", summary = "删除#(description)")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "#(description)id", schema = @Schema(type = "#(api.parameters.id.type)"))
  @DeleteMapping("{id}")
  Response delete(@EditableId(#(name)Entity.class) @PathVariable("id") #(entity.properties.id.type) id);

  class #(name)PageResult extends DataResponse<Page<#(name)Entity>> {

  }

  class #(name)Result extends DataResponse<#(name)Entity> {

  }
}
', 1, 1),
       (2, 'api/ApiImpl.java', '接口实现类代码模板', 'package #(package).api;

import #(package).entity.#(name)Entity;
import #(package).service.#(name)Service;

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
  public DataResponse<#(name)Entity> load(#(entity.properties.id.type) id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  public DataResponse<#(name)Entity> create(#(name)Entity entity) {
    service.create(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(#(entity.properties.id.type) id, #(name)Entity entity) {
    entity.setIdIfAbsent(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(#(entity.properties.id.type) id) {
    service.delete(#(name)Entity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
', 1, 1),
       (3, 'entity/Entity.java', '数据实体类代码模板', 'package #(package).entity;

#for(import: entity.imports)
import #(import);
#end
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
public class #(name)Entity implements Persistable<#(entity.properties.id.type)>#for(interface: entity.interfaces), #(interface)#end  {

  @Schema(description = "#(entity.properties.id.description)")
  private #(entity.properties.id.type) id;
#for(property: entity.properties.others)

#for(validation: property.validations)
  @#(validation.name)(#for(parameter : validation.parameters)#(parameter.key) = #(parameter.value), #end message = "#(validation.message)", groups = {#for(group : validation.groups)#(group).class#if(!for.last), #end #end})
#end
  @Schema(description = "#(property.description)")
  private #(property.type) #(property.name);
#end
}
', 1, 1),
       (4, 'service/Service.java', '服务类代码模板', 'package #(package).service;

import #(package).entity.#(name)Entity;
import #(package).repository.#(name)Repository;

import cn.koala.mybatis.AbstractMyBatisService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * #(description)服务类
 *
 * @author Koala Code Generator
 */
@RequiredArgsConstructor
@Getter
public class #(name)Service extends AbstractMyBatisService<#(name)Entity, #(entity.properties.id.type)> {

  protected final #(name)Repository repository;
}
', 1, 1),
       (5, 'repository/Repository.java', '仓库接口代码模板', 'package #(package).repository;

import #(package).entity.#(name)Entity;

import cn.koala.persist.repository.CrudRepository;

/**
 * #(description)仓库接口
 *
 * @author Koala Code Generator
 */
public interface #(name)Repository extends CrudRepository<#(name)Entity, #(entity.properties.id.type)> {
}
', 1, 1),
       (6, 'mapper/Mapper.xml', 'Mapper文件代码模板', '<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="#(package).repository.#(name)Repository">

  <sql id="select#(name)">
    select
#for(column : columns)
    t.#(column.name)#if(!for.last),#end
#end
    from #(table.name) t
  </sql>

  <sql id="orderBy">
    <choose>
      <when test="orders != null and orders.size() > 0">
        <foreach collection="orders" item="order" index="index" open=" order by " close="" separator=",">
          <include refid="orderByField"/>
        </foreach>
      </when>
      <otherwise>
#if(mybatis.isAuditable())
        order by t.created_time desc
#else
		order by t.id asc
#end
      </otherwise>
    </choose>
  </sql>

  <sql id="orderByField">
#for(column: mybatis.columns)
    <if test="order.property == ''#(column.propertyName)''">
        t.#(column.columnName) <include refid="cn.koala.mybatis.repository.CommonRepository.orderDirection" />
    </if>
#end
  </sql>

  <select id="list" resultType="#(package).entity.#(name)Entity">
    <include refid="select#(name)"/>
    <where>
#if(mybatis.isStateful())
      t.is_deleted = ${@cn.koala.persist.domain.YesNo@NO.value}
#end
#for(column: mybatis.columns)
#if(column.columnName != ''id'')
      <if test="#(column.propertyName) != null and #(column.propertyName) != ''''">
       and t.#(column.columnName) = #{#(column.propertyName)}
      </if>
#end
#end
    </where>
	<include refid="orderBy"/>
  </select>

  <select id="load" resultType="#(package).entity.#(name)Entity">
    <include refid="select#(name)"/>
    where#if(mybatis.isStateful()) t.is_deleted = ${@cn.koala.persist.domain.YesNo@NO.value} and#end  t.id=#{id}
  </select>

  <insert id="create" parameterType="#(package).entity.#(name)Entity"  useGeneratedKeys="true" keyProperty="id">
    insert into #(table.name)
	value (
#for(column: mybatis.columns)
    #{#(column.propertyName)}#if(!for.last),#end
#end
    )
  </insert>

  <update id="update" parameterType="#(package).entity.#(name)Entity">
    update #(table.name)
    <trim prefix="set" suffixOverrides=",">
#for(column: mybatis.columns)
#if(column.columnName != ''id'')
      <if test="#(column.propertyName) != null">#(column.columnName)=#{#(column.propertyName)},</if>
#end
#end
    </trim>
    where#if(mybatis.isStateful()) is_deleted = ${@cn.koala.persist.domain.YesNo@NO.value} and#end  id = #{id}
  </update>

#if(mybatis.isStateful())
  <update id="delete" parameterType="#(package).entity.#(name)Entity">
    update #(table.name)
    set is_deleted   = ${@cn.koala.persist.domain.YesNo@YES.value}#if(mybatis.isAuditable()),#end
#if(mybatis.isAuditable())
        deleted_by   = #{deletedBy},
        deleted_time = #{deletedTime}
#end
    where id = #{id}
  </update>
#else
  <delete id="delete" parameterType="#(package).entity.#(name)Entity">
    delete from #(table.name) where id = #{id}
  </delete>
#end
</mapper>
', 1, 1);