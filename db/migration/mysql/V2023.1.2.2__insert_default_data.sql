-- 默认部门
insert into k_department(id, name, parent_id, sort_index, is_systemic, created_by, created_time)
values (1, '考拉开源', null, 1, 1, 1, now());

-- 演示数据库
insert into t_database(id, name, url, username, password, catalog, `schema`, is_systemic)
values (1, '演示数据库', 'jdbc:mysql://127.0.0.1:3306/koala_demo', 'koala_demo', 'koala_demo',
        'koala_demo', 'koala_demo', 1);

-- 考拉代码模板
-- 考拉代码-服务端
insert into t_template_group(id, name, remark, is_systemic)
values (1, '考拉代码-服务端', '考拉服务端代码生成模板', 1);

insert into t_template(id, name, remark, content, group_id, is_systemic)
values (101, 'api/#(name.pascal.singular)Api.java', '接口代码模板', 'package #(package).api;

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
  @PreAuthorize("hasAuthority(''#(name.kebab.singular).read'')")
  @Operation(operationId = "list#(name.pascal.plural)", summary = "根据条件分页查询#(description)")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = #(name.pascal.singular)PageResult.class))}
  )
#for(property: properties)
  #if(!parameterIgnoredPropertyNames.contains(property.name.camel.singular))
  @Parameter(in = ParameterIn.QUERY, name = "#(property.name.camel.singular)", description = "#(property.description)", schema = @Schema(type = "#(property.type.json)"))
  #end
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
  @PreAuthorize("hasAuthority(''#(name.kebab.singular).read'')")
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
  @PreAuthorize("hasAuthority(''#(name.kebab.singular).create'')")
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
  @PreAuthorize("hasAuthority(''#(name.kebab.singular).update'')")
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
  @PreAuthorize("hasAuthority(''#(name.kebab.singular).delete'')")
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
', 1, 1),
       (102, 'api/#(name.pascal.singular)ApiImpl.java', '接口实现类代码模板', 'package #(package).api;

import #(package).entity.#(name.pascal.singular)Entity;
import #(package).service.#(name.pascal.singular)Service;

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
 * @author Koala Code Gen
 */
@RestController
@RequiredArgsConstructor
public class #(name.pascal.singular)ApiImpl implements #(name.pascal.singular)Api {

  private final #(name.pascal.singular)Service service;

  @Override
  public DataResponse<Page<#(name.pascal.singular)Entity>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.page(parameters, pageable));
  }

  @Override
  public DataResponse<#(name.pascal.singular)Entity> load(#(id.type.java) id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  public DataResponse<#(name.pascal.singular)Entity> create(#(name.pascal.singular)Entity entity) {
    service.create(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(#(id.type.java) id, #(name.pascal.singular)Entity entity) {
    entity.setIdIfAbsent(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(#(id.type.java) id) {
    service.delete(#(name.pascal.singular)Entity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
', 1, 1),
       (103, 'entity/#(name.pascal.singular)Entity.java', '数据实体类代码模板', 'package #(package).entity;

#if(entity.isAbstract)
import cn.koala.mybatis.AbstractEntity;
#else
import cn.koala.persist.domain.Persistable;
#end
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
#if(entity.isAbstract)
import lombok.EqualsAndHashCode;
#end
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;



/**
 * #(description)数据实体类
 *
 * @author Koala Code Gen
 */
@Data
#if(entity.isAbstract)
@EqualsAndHashCode(callSuper = true)
#end
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "#(description)数据实体类")
public class #(name.pascal.singular)Entity#if(entity.isAbstract) extends AbstractEntity#else  implements Persistable#end<#(id.type.java)> {
#if(!entity.isAbstract)

  @Schema(description = "#(id.description)")
  private #(id.type.java) id;
#end
#for(property: properties)
  #if(entity.isAbstract)
    #if(!entity.abstractIgnoredPropertyNames.contains(property.name.camel.singular))

      #if(entity.validations.containsKey(property.name.camel.singular))
        #for(validation: entity.validations.get(property.name.camel.singular))
  @#(validation.name)(#for(parameter : validation.parameters)#(parameter.key) = #(parameter.value), #end message = "#(validation.message)", groups = {#for(group : validation.groups)#(group).class#if(!for.last), #end #end})
        #end
      #end
  @Schema(description = "#(property.description)")
  private #(property.type.java) #(property.name.camel.singular);
    #end
  #else

	#if(entity.validations.containsKey(property.name.camel.singular))
        #for(validation: entity.validations.get(property.name.camel.singular))
  @#(validation.name)(#for(parameter : validation.parameters)#(parameter.key) = #(parameter.value), #end message = "#(validation.message)", groups = {#for(group : validation.groups)#(group).class#if(!for.last), #end #end})
        #end
      #end
  @Schema(description = "#(property.description)")
  private #(property.type.java) #(property.name.camel.singular);
  #end
#end
}
', 1, 1),
       (104, 'service/#(name.pascal.singular)Service.java', '服务类代码模板', 'package #(package).service;

import #(package).entity.#(name.pascal.singular)Entity;
import #(package).repository.#(name.pascal.singular)Repository;
import cn.koala.mybatis.AbstractMyBatisService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * #(description)服务类
 *
 * @author Koala Code Gen
 */
@Component
@Getter
@RequiredArgsConstructor
public class #(name.pascal.singular)Service extends AbstractMyBatisService<#(name.pascal.singular)Entity, #(id.type.java)> {

  private final #(name.pascal.singular)Repository repository;
}
', 1, 1),
       (105, 'repository/#(name.pascal.singular)Repository.java', '仓库接口代码模板', 'package #(package).repository;

import #(package).entity.#(name.pascal.singular)Entity;

import cn.koala.persist.CrudRepository;

/**
 * #(description)仓库接口
 *
 * @author Koala Code Gen
 */
public interface #(name.pascal.singular)Repository extends CrudRepository<#(name.pascal.singular)Entity, #(id.type.java)> {
}
', 1, 1),
       (106, '#(name.pascal.singular)Mapper.xml', 'Mapper文件代码模板', '<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="#(package).repository.#(name.pascal.singular)Repository">

  <sql id="select#(name.pascal.singular)">
    select t.id,
#for(property: properties)
		   t.#(property.name.snake.singular)#if(!for.last),#end
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
#if(entity.isAbstract)
        order by t.created_time desc
#else
		order by t.id asc
#end
      </otherwise>
    </choose>
  </sql>

  <sql id="orderByField">
#for(property: properties)
    <if test="order.property == ''#(property.name.camel.singular)''">
        t.#(property.name.snake.singular) <include refid="cn.koala.mybatis.repository.CommonRepository.orderDirection" />
    </if>
#end
  </sql>

  <select id="list" resultType="#(package).entity.#(name.pascal.singular)Entity">
    <include refid="select#(name.pascal.singular)"/>
    <where>
#if(entity.isAbstract)
      t.is_deleted = ${@cn.koala.persist.domain.YesNo@NO.value}
#end
#for(property: properties)
      <if test="#(property.name.camel.singular) != null and #(property.name.camel.singular) != ''''">
       and t.#(property.name.snake.singular) = #{#(property.name.camel.singular)}
      </if>
#end
    </where>
	<include refid="orderBy"/>
  </select>

  <select id="load" resultType="#(package).entity.#(name.pascal.singular)Entity">
    <include refid="select#(name.pascal.singular)"/>
    where#if(entity.isAbstract) t.is_deleted = ${@cn.koala.persist.domain.YesNo@NO.value} and#end  t.id=#{id}
  </select>

  <insert id="create" parameterType="#(package).entity.#(name.pascal.singular)Entity"  useGeneratedKeys="true" keyProperty="id">
    insert into t_biological_information_log
      value (
			 #{id},
#for(property: properties)
			 #{#(property.name.camel.singular)}#if(!for.last),#end
#end
	  )
  </insert>

  <update id="update" parameterType="#(package).entity.#(name.pascal.singular)Entity">
    update #(table.name)
    <trim prefix="set" suffixOverrides=",">
#for(property: properties)
      <if test="#(property.name.camel.singular) != null">#(property.name.snake.singular)=#{#(property.name.camel.singular)},</if>
#end
    </trim>
    where#if(entity.isAbstract) is_deleted = ${@cn.koala.persist.domain.YesNo@NO.value} and#end  id = #{id}
  </update>

#if(entity.isAbstract)
  <update id="delete" parameterType="#(package).entity.#(name.pascal.singular)Entity">
    update #(table.name)
    set is_deleted   = ${@cn.koala.persist.domain.YesNo@YES.value},
        deleted_by   = #{deletedBy},
        deleted_time = #{deletedTime}
    where id = #{id}
  </update>
#else
  <delete id="delete" parameterType="#(package).entity.#(name.pascal.singular)Entity">
    delete from #(table.name) where id = #{id}
  </delete>
#end
</mapper>
', 1, 1),
       (107, 'config/#(name.pascal.singular)PermissionRegistrar.java', '权限注册器代码模板', 'package #(package).config;

import cn.koala.system.support.CrudPermissionRegistrar;
import org.springframework.stereotype.Component;

/**
 * TODO: 请修改排序索引, 建议业务功能从30000开始, 30000以下为系统保留权限
 * #(description)权限注册器
 *
 * @author Koala Code Generator
 */
@Component
public class #(name.pascal.singular)PermissionRegistrar extends CrudPermissionRegistrar {

  public #(name.pascal.singular)PermissionRegistrar() {
    super("#(name.kebab.singular)", "#(description)管理", 30000, null);
  }
}
', 1, 1);

-- 考拉代码-客户端
insert into t_template_group(id, name, remark, is_systemic)
values (2, '考拉代码-客户端', '考拉客户端代码生成模板', 1);

insert into t_template(id, name, remark, content, group_id, is_systemic)
values (201, 'apis/#(name.kebab.singular)/index.ts', '接口请求代码模板', 'import { defHttp } from ''/@/utils/http/axios'';

import type SearchParameters from ''../SearchParameters'';
import type PageResult from ''../PageResult'';
import type #(name.pascal.singular)Entity from ''./#(name.pascal.singular)Entity'';

const domain = ''/#(name.kebab.plural)'';

export function list#(name.pascal.singular)(params: SearchParameters) {
  return defHttp.get<PageResult<#(name.pascal.singular)Entity>>({ url: domain, params }, { joinParamsToUrl: true });
}

export function load#(name.pascal.singular)(id: number) {
  return defHttp.get<#(name.pascal.singular)Entity>({ url: `${domain}/${id}` });
}

export function create#(name.pascal.singular)(data: #(name.pascal.singular)Entity) {
  return defHttp.post<#(name.pascal.singular)Entity>({ url: domain, data });
}

export function update#(name.pascal.singular)(id: number, data: #(name.pascal.singular)Entity) {
  return defHttp.put<null>({ url: `${domain}/${id}`, data });
}

export function delete#(name.pascal.singular)(id: number) {
  return defHttp.delete<null>({ url: `${domain}/${id}` });
}

export { #(name.pascal.singular)Entity };
', 2, 1),
       (202, 'apis/#(name.kebab.singular)/#(name.pascal.singular)Entity.ts', '数据实体类代码模板', '#if(entity.isAbstract)
import type AbstractEntity from ''../AbstractEntity'';

#end
export default interface #(name.pascal.singular)Entity#if(entity.isAbstract) extends AbstractEntity#end  {
#for(property: properties)
  #if(entity.isAbstract)
    #if(!entity.abstractIgnoredPropertyNames.contains(property.name.camel.singular))
  #(property.name.camel.singular): #(property.type.ts)
	#end
  #else
  #(property.name.camel.singular): #(property.type.ts)
  #end
#end
}
', 2, 1),
       (203, 'views/#(name.kebab.singular)/#(name.kebab.singular).data.ts', '页面数据代码模板', 'import { BasicColumn, FormSchema } from ''/@/components/Table'';

export const columns: BasicColumn[] = [
#for(property: properties)
  {
    title: ''#(property.description)'',
    dataIndex: ''#(property.name.camel.singular)'',
  },
#end
];

export const searchFormSchema: FormSchema[] = [
#for(property: properties)
  {
    field: ''#(property.name.camel.singular)'',
    label: ''#(property.description)'',
    component: ''#(property.type.vben)'',
    colProps: {
  	  xl: 12,
  	  xxl: 8,
    },
  },
#end
];

export const formSchema: FormSchema[] = [
#for(property: properties)
  {
    field: ''#(property.name.camel.singular)'',
    label: ''#(property.description)'',
    component: ''#(property.type.vben)'',
  },
#end
];
', 2, 1),
       (204, 'views/#(name.kebab.singular)/index.vue', '列表页代码模板', '<script lang="ts" setup>
  import { BasicTable, TableAction, useTable } from ''/@/components/Table'';
  import { useModal } from ''/@/components/Modal'';
  import { list#(name.pascal.singular), delete#(name.pascal.singular) } from ''/@/apis/#(name.kebab.singular)'';
  import { YesNo } from ''/@/enums/YesNo'';
  import #(name.pascal.singular)Modal from ''./#(name.pascal.singular)Modal.vue'';
  import { columns, searchFormSchema } from ''./#(name.kebab.singular).data'';

  const [register, { reload }] = useTable({
    title: ''#(description)列表'',
    columns: columns,
    actionColumn: {
      width: 120,
      title: ''操作'',
      dataIndex: ''action'',
      fixed: undefined,
    },
    api: list#(name.pascal.singular),
    showIndexColumn: false,
    bordered: true,
    showTableSetting: true,
    canResize: false,
    useSearchForm: true,
    formConfig: {
      labelWidth: 100,
      schemas: searchFormSchema,
    },
  });
  const [registerModal, { openModal }] = useModal();
  function handleCreate() {
    openModal(true, {
      isUpdate: false,
    });
  }
  function handleEdit(record: Recordable) {
    openModal(true, {
      record,
      isUpdate: true,
    });
  }
  async function handleDelete(record: Recordable) {
    await delete#(name.pascal.singular)(record.id);
    reload();
  }
  function handleSuccess() {
    reload();
  }
</script>
<template>
  <div>
    <basic-table @register="register">
      <template #toolbar>
        <a-button v-auth="''#(name.kebab.singular).create''" type="primary" @click="handleCreate"> 新增#(description) </a-button>
      </template>
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === ''action''">
          <table-action
            :actions="[
              {
                icon: ''clarity:note-edit-line'',
                tooltip: ''编辑'',
                auth: ''#(name.kebab.singular).update'',
                onClick: handleEdit.bind(null, record),
              },
              {
                icon: ''ant-design:delete-outlined'',
                tooltip: ''删除'',
                color: ''error'',
                auth: ''#(name.kebab.singular).delete'',
                popConfirm: {
                  title: ''是否确认删除'',
                  placement: ''left'',
                  confirm: handleDelete.bind(null, record),
                },
              },
            ]"
          />
        </template>
      </template>
    </basic-table>
    <#(name.kebab.singular)-modal @register="registerModal" @success="handleSuccess" />
  </div>
</template>
', 2, 1),
       (205, 'views/#(name.kebab.singular)/#(name.pascal.singular)Modal.vue', '表单弹窗代码模板', '<script lang="ts" setup>
  import { ref, unref, computed } from ''vue'';
  import { BasicModal, useModalInner } from ''/@/components/Modal'';
  import { BasicForm, useForm } from ''/@/components/Form/index'';
  import { formSchema } from ''./#(name.kebab.singular).data'';
  import { create#(name.pascal.singular), update#(name.pascal.singular) } from ''/@/apis/#(name.kebab.singular)'';
  const isUpdate = ref(false);
  const id = ref<number | null>(null);
  const getTitle = computed(() => (!unref(isUpdate) ? ''新增#(description)'' : ''编辑#(description)''));
  const [registerForm, { resetFields, setFieldsValue, validate }] = useForm({
    labelWidth: 100,
    baseColProps: { span: 24 },
    schemas: formSchema,
    showActionButtonGroup: false,
  });
  const [registerModal, { setModalProps, closeModal }] = useModalInner(async (data) => {
    resetFields();
    setModalProps({ confirmLoading: false });
    isUpdate.value = !!data?.isUpdate;
    if (unref(isUpdate)) {
      id.value = data.record.id;
      setFieldsValue({
        ...data.record,
      });
    }
  });
  const emit = defineEmits([''success'', ''register'']);
  async function handleSubmit() {
    try {
      const values = await validate();
      setModalProps({ confirmLoading: true });
      if (unref(isUpdate)) {
        await update#(name.pascal.singular)(unref(id)!, values);
      } else {
        await create#(name.pascal.singular)(values);
      }
      closeModal();
      emit(''success'');
    } finally {
      setModalProps({ confirmLoading: false });
    }
  }
</script>
<template>
  <basic-modal v-bind="$attrs" @register="registerModal" :title="getTitle" @ok="handleSubmit">
    <basic-form @register="registerForm" />
  </basic-modal>
</template>
', 2, 1);