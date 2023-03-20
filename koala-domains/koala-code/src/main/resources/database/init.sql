insert into t_database(name, url, username, password, catalog, `schema`, is_system)
values ('演示数据库', 'jdbc:mysql://bj-cdb-9amt73r4.sql.tencentcdb.com:59997/koala_demo', 'koala_demo', 'koala_demo',
        'koala_demo', 'koala_demo', 1);

insert into t_template_group(id, name, remark, is_system)
values (999, '考拉代码', '考拉代码生成模板', 1);

insert into t_template(name, remark, content, group_id, is_system)
values ('Api.java', '接口代码模板', 'package #(package).apis;

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
  @Operation(summary = "根据条件分页查询#(description)")
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
  @Operation(summary = "根据id查询#(description)")
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
  @Operation(summary = "创建数据实体")
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
  @Operation(summary = "更新#(description)")
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
  @Operation(summary = "删除#(description)")
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
       ('ApiImpl.java', '接口实现类代码模板', 'package #(package).apis;

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
       ('Entity.java', '数据实体类代码模板', 'package #(package).entities;

#if(implements.contains(''AuditModel<Long>''))
import cn.koala.mybatis.AuditModel;
#end
import cn.koala.mybatis.IdModel;
#if(implements.contains(''SortModel''))
import cn.koala.mybatis.SortModel;
#end
#if(implements.contains(''StateModel''))
import cn.koala.mybatis.StateModel;
import cn.koala.mybatis.YesNo;
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
public class #(name)Entity implements IdModel<#(id.javaType)>#for(implement: implements), #(implement)#end  {
#for(property: properties)
  @Schema(description = "#(property.description)")
  private #(property.javaType) #(property.name);
#end
}
', 999, 1),
       ('Service.java', '服务类代码模板', 'package #(package).services;

import #(package).#(name)Entity;
import #(package).#(name)Repository;
import cn.koala.mybatis.BaseService;

/**
 * #(description)服务类
 *
 * @author Koala Code Generator
 */
public class #(name)Service extends BaseService<#(name)Entity, #(id.javaType)> {
  /**
   * 构造函数
   *
   * @param repository 仓库接口
   */
  public #(name)Service(#(name)Repository repository) {
    super(repository, (entity) -> null);
  }
}
', 999, 1),
       ('Repository.java', '仓库接口代码模板', 'package #(package).repositories;

import #(package).#(name)Entity;
import cn.koala.mybatis.CrudRepository;

/**
 * #(description)仓库接口
 *
 * @author Koala Code Generator
 */
public interface #(name)Repository extends CrudRepository<#(name)Entity, #(id.javaType)> {
}
', 999, 1),
       ('Mapper.xml', 'Mapper文件代码模板', '<?xml version="1.0" encoding="UTF-8" ?>
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

  <insert id="insert" parameterType="#(package).entities.#(name)Entity">
    insert into #(table.name)
	value (
#for(property : properties)
    #{#(property.name)}#if(!for.last),#end
#end
    )
  </insert>

#if(implements.contains(''StateModel''))
  <update id="deleteById" parameterType="#(package).entities.#(name)Entity">
    update #(table.name)
    set is_delete      = ${@cn.koala.mybatis.YesNo@YES.value}#if(auditModel),#end
#if(implements.contains(''AuditModel<Long>''))
        delete_user_id = #{deleteUserId},
        delete_time    = #{deleteTime}
#end
    where id = #{id}
  </update>
#else
  <delete id="deleteById" parameterType="#(package).entities.#(name)Entity">
    delete from #(table.name) where id = #{id}
  </delete>
#end

  <update id="updateById" parameterType="#(package).entities.#(name)Entity">
    update #(table.name)
    <trim prefix="set" suffixOverrides=",">
#for(i = 0; i < columns.size(); i++)
      <if test="#(properties[i].name) != null">#(columns[i].name)=#{#(properties[i].name)},</if>
#end
    </trim>
    where#if(implements.contains(''StateModel'')) is_delete = ${@cn.koala.mybatis.YesNo@NO.value} and#end  id=#{id}
  </update>

  <select id="findById" resultType="#(package).entities.#(name)Entity">
    <include refid="select#(name)"/>
    where#if(implements.contains(''StateModel'')) t.is_delete = ${@cn.koala.mybatis.YesNo@NO.value} and#end  t.id=#{id}
  </select>

  <select id="findAll" resultType="#(package).entities.#(name)Entity">
    <include refid="select#(name)"/>
    <where>
#if(implements.contains(''StateModel''))
      t.is_delete = ${@cn.koala.mybatis.YesNo@NO.value}
#end
#for(i = 0; i < columns.size(); i++)
      <if test="parameters.#(properties[i].name) != null and parameters.#(properties[i].name) != ''''">
       #if(implements.contains(''StateModel'')) and#end  t.#(columns[i].name) = #{parameters.#(properties[i].name)}
      </if>
#end
    </where>
  </select>
</mapper>
', 999, 1);