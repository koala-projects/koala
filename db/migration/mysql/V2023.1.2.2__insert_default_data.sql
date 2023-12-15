-- 默认部门
insert into k_department(id, parent_id, name, sort_index, systemic, created_by, created_date)
values (1, null, '考拉开源', 1, 'YES', 1, now());

-- 演示数据库
insert into k_database(id, name, url, username, password, catalog, `schema`, systemic, created_by, created_date)
values (1, '演示数据库', 'jdbc:mysql://127.0.0.1:3306/koala_demo', 'koala', 'Koala@2023', 'koala_demo', 'koala_demo',
        'YES', 1, now());

-- 考拉代码模板
-- 考拉代码-服务端
insert into k_template_group(id, name, description, systemic, created_by, created_date)
values (1, '考拉代码-服务端', '考拉服务端代码生成模板', 'YES', 1, now());

insert into k_template(id, group_id, name, description, content, systemic, created_by, created_date)
values (101, 1, 'api/#(name.pascal.singular)Api.java', '接口代码模板', 'package #(package).api;

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
', 'YES', 1, now()),
       (102, 1, 'api/#(name.pascal.singular)ApiImpl.java', '接口实现类代码模板', 'package #(package).api;

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
    entity.setId(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(#(id.type.java) id) {
    service.delete(#(name.pascal.singular)Entity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
', 'YES', 1, now()),
       (103, 1, 'entity/#(name.pascal.singular)Entity.java', '数据实体类代码模板', 'package #(package).entity;

#if(abstract)
import cn.koala.mybatis.domain.AbstractEntity;
#else
#for(import: imports)
#(import)
#end
#end
import cn.koala.validation.group.Create;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
#if(abstract)
import lombok.EqualsAndHashCode;
#end
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * #(description)数据实体类
 *
 * @author Koala Code Gen
 */
@Data
#if(abstract)
@EqualsAndHashCode(callSuper = true)
#end
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "#(description)实体")
#if(abstract)
public class #(name.pascal.singular)Entity extends AbstractEntity<Long, #(id.type.java)> implements Serializable {
#else
public class #(name.pascal.singular)Entity implements#for(implement: implements) #(implement),#end  Serializable {
#end

  @Serial
  private static final long serialVersionUID = 2023_02_00L;
#if(!abstract)

  @Schema(description = "#(id.description)")
  private #(id.type.java) id;
#end
#for(property: koala.properties)

  @Schema(description = "#(property.description)")
#for(validation: property.validations)
  #(validation)
#end
  private #(property.type) #(property.name);
#end
}
', 'YES', 1, now()),
       (104, 1, 'service/#(name.pascal.singular)Service.java', '服务类代码模板', 'package #(package).service;

import #(package).entity.#(name.pascal.singular)Entity;
import #(package).repository.#(name.pascal.singular)Repository;
import cn.koala.mybatis.service.AbstractSmartService;
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
public class #(name.pascal.singular)Service extends AbstractSmartService<Long, #(name.pascal.singular)Entity, #(id.type.java)> {

  private final #(name.pascal.singular)Repository repository;

  private final AuditorAware<Long> auditorAware;
}
', 'YES', 1, now()),
       (105, 1, 'repository/#(name.pascal.singular)Repository.java', '仓库接口代码模板', 'package #(package).repository;

import #(package).entity.#(name.pascal.singular)Entity;
import cn.koala.mybatis.repository.CrudRepository;

/**
 * #(description)仓库接口
 *
 * @author Koala Code Gen
 */
public interface #(name.pascal.singular)Repository extends CrudRepository<#(name.pascal.singular)Entity, #(id.type.java)> {
}
', 'YES', 1, now()),
       (106, 1, '#(name.pascal.singular)Mapper.xml', 'Mapper文件代码模板', '<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="#(package).repository.#(name.pascal.singular)Repository">

  <sql id="select">
    select t.id,
#for(property: properties)
		   t.#(property.name.snake.singular)#if(!for.last),#end
#end
    from #(table.name) t
  </sql>

  <sql id="where">
	<where>
#for(property: properties)
      <if test="#(property.name.camel.singular) != null and #(property.name.camel.singular) != ''''">
       and t.#(property.name.snake.singular) = #{#(property.name.camel.singular)}
      </if>
#end
    </where>
  </sql>

  <sql id="orders">
    <choose>
      <when test="pageable != null and pageable.getSort() != null and pageable.getSort().isSorted()">
        <foreach collection="pageable.getSort().toList()" item="order" index="index" open=" order by " close=""
                 separator=",">
          <include refid="order"/>
        </foreach>
      </when>
      <otherwise>
#if(sortable)
        order by t.sort_index asc
#else if(auditable)
		order by t.created_date desc
#else
		order by t.id
#end
      </otherwise>
    </choose>
  </sql>

  <sql id="order">
#for(property: properties)
    <if test="order.property == ''#(property.name.camel.singular)''">
        t.#(property.name.snake.singular)
		<include refid="cn.koala.mybatis.common.CommonRepository.orderDirection" />
    </if>
#end
  </sql>

  <select id="list" resultType="#(package).entity.#(name.pascal.singular)Entity">
    <include refid="select"/>
	<include refid="where"/>
	<include refid="orders"/>
  </select>

  <select id="load" resultType="#(package).entity.#(name.pascal.singular)Entity">
    <include refid="select"/>
    where t.id = #{id}
  </select>

  <insert id="create" parameterType="#(package).entity.#(name.pascal.singular)Entity"  useGeneratedKeys="true" keyProperty="id">
    insert into #(table.name)
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
    where id = #{id}
  </update>

  <delete id="delete" parameterType="#(package).entity.#(name.pascal.singular)Entity">
	delete
    from #(table.name)
    where id = #{id}
  </delete>
</mapper>
', 'YES', 1, now()),
       (107, 1, 'config/#(name.pascal.singular)PermissionRegistrar.java', '权限注册器代码模板', 'package #(package).config;

import cn.koala.system.permission.CrudPermissionRegistrar;
import org.springframework.stereotype.Component;

/**
 * TODO: 请修改排序索引, 建议业务功能从30000开始, 30000以下为系统保留权限
 * #(description)权限注册器
 *
 * @author Koala Code Generator
 */
@Component
public class #(name.pascal.singular)PermissionRegistrar extends CrudPermissionRegistrar {

  public #(name.pascal.singular)PermissionRegistrar(){
    super("#(name.kebab.singular)", "#(description)管理", 30000, null);
  }
}
', 'YES', 1, now());

-- 考拉代码-客户端
insert into k_template_group(id, name, description, systemic, created_by, created_date)
values (2, '考拉代码-客户端', '考拉客户端代码生成模板', 'YES', 1, now());

insert into k_template(id, group_id, name, description, content, systemic, created_by, created_date)
values (201, 2, 'apis/#(name.kebab.singular)/index.ts', '接口请求代码模板', 'import { defHttp } from ''/@/utils/http/axios'';

import type SearchParameters from ''../SearchParameters'';
import type PageResult from ''../PageResult'';
import type #(name.pascal.singular)Entity from ''./#(name.pascal.singular)Entity'';

const domain = ''/#(name.kebab.plural)'';

export function list#(name.pascal.singular)(params: SearchParameters) {
  return defHttp.get<PageResult<#(name.pascal.singular)Entity>>({ url: domain, params }, { joinParamsToUrl: true });
}

export function load#(name.pascal.singular)(id: #(id.type.ts)) {
  return defHttp.get<#(name.pascal.singular)Entity>({ url: `${domain}/${id}` });
}

export function create#(name.pascal.singular)(data: #(name.pascal.singular)Entity) {
  return defHttp.post<#(name.pascal.singular)Entity>({ url: domain, data });
}

export function update#(name.pascal.singular)(id: #(id.type.ts), data: #(name.pascal.singular)Entity) {
  return defHttp.put<null>({ url: `${domain}/${id}`, data });
}

export function delete#(name.pascal.singular)(id: #(id.type.ts)) {
  return defHttp.delete<null>({ url: `${domain}/${id}` });
}

export type { #(name.pascal.singular)Entity };
', 'YES', 1, now()),
       (202, 2, 'apis/#(name.kebab.singular)/#(name.pascal.singular)Entity.ts', '数据实体类代码模板', 'interface #(name.pascal.singular)Entity {
  id: #(id.type.ts);
#for(property: koalaAdmin.properties)
  #(property.name): #(property.type);
#end
}

export default #(name.pascal.singular)Entity;
', 'YES', 1, now()),
       (203, 2, 'views/#(name.kebab.singular)/#(name.kebab.singular).data.ts', '页面数据代码模板', 'import { BasicColumn, FormSchema } from ''/@/components/Table'';

export const columns: BasicColumn[] = [
#for(property: koalaAdmin.properties)
  {
    title: ''#(property.description)'',
    dataIndex: ''#(property.name)'',
  },
#end
];

export const searchFormSchema: FormSchema[] = [
#for(property: koalaAdmin.properties)
  {
    field: ''#(property.name)'',
    label: ''#(property.description)'',
    component: ''#(property.component)'',
    colProps: {
  	  xl: 12,
  	  xxl: 8,
    },
  },
#end
];

export const formSchema: FormSchema[] = [
#for(property: koalaAdmin.properties)
  {
    field: ''#(property.name)'',
    label: ''#(property.description)'',
    component: ''#(property.component)'',
  },
#end
];
', 'YES', 1, now()),
       (204, 2, 'views/#(name.kebab.singular)/index.vue', '列表页代码模板', '<script lang="ts" setup>
  import { BasicTable, TableAction, useTable } from ''/@/components/Table'';
  import { useModal } from ''/@/components/Modal'';
  import { list#(name.pascal.singular), delete#(name.pascal.singular) } from ''/@/apis/#(name.kebab.plural)'';
#if(abstract)
  import { YesNo } from ''/@/enums/YesNo'';
#end
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
#if(abstract)
				ifShow: record.systemic === YesNo.NO,
#end
                onClick: handleEdit.bind(null, record),
              },
              {
                icon: ''ant-design:delete-outlined'',
                tooltip: ''删除'',
                color: ''error'',
                auth: ''#(name.kebab.singular).delete'',
#if(abstract)
				ifShow: record.systemic === YesNo.NO,
#end
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
', 'YES', 1, now()),
       (205, 2, 'views/#(name.kebab.singular)/#(name.pascal.singular)Modal.vue', '表单弹窗代码模板', '<script lang="ts" setup>
  import { ref, unref, computed } from ''vue'';
  import { BasicModal, useModalInner } from ''/@/components/Modal'';
  import { BasicForm, useForm } from ''/@/components/Form/index'';
  import { formSchema } from ''./#(name.kebab.singular).data'';
  import { #(name.pascal.singular)Entity, create#(name.pascal.singular), update#(name.pascal.singular) } from ''/@/apis/#(name.kebab.plural)'';

  const isUpdate = ref(false);
  const id = ref<#(id.type.ts) | null>(null);
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
      const values: #(name.pascal.singular)Entity = await validate();
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
', 'YES', 1, now());