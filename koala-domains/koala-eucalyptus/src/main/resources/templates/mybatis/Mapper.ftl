<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- namespace必须指向Dao接口 -->
<mapper namespace="${packageName}.repositories.${domain.code.capitalize}Repository">
  <sql id="select${domain.code.capitalize}">
    select
    <#list domain.properties as property>
    t.${property.column.name} as "${property.code}"<#sep>,</#sep>
    </#list>
    from ${domain.tableName} t
  </sql>
  <insert id="add" parameterType="${packageName}.entities.${domain.code.capitalize}Entity">
    insert into ${domain.tableName} (
    <#list domain.properties as property>
        ${property.column.name}<#sep>,</#sep>
    </#list>
    ) values (
    <#list domain.properties as property>
    #{${property.code}, jdbcType=${property.column.type}<#sep>,</#sep>
    </#list>
    )
  </insert>
  <update id="delete" parameterType="${packageName}.entities.${domain.code.capitalize}Entity">
    <#if domain.idProperty??>
    delete from ${domain.tableName} where ${domain.idProperty.column.name} = #{${domain.idProperty.code}, jdbcType=${domain.idProperty.column.type}}
    </#if>
  </update>
  <select id="findAll" resultType="${packageName}.entities.${domain.code.capitalize}Entity">
    <include refid="select${domain.code.capitalize}"/>
    <where>
      <#list searchParameters as parameter>
      <if test='parameters.${parameter.code} != null and parameters.${parameter.code}!=""'>
        and t.${parameter.property.column.name} = #{parameters.${parameter.code}}
      </if>
      </#list>
    </where>
  </select>
  <select id="findById" resultType="${packageName}.entities.${domain.code.capitalize}Entity">
    <#if domain.idProperty??>
    <include refid="select${domain.code.capitalize}"/>
    where t.${domain.idProperty.column.name} = #{${domain.idProperty.code}, jdbcType=${domain.idProperty.column.type}}
    </#if>
  </select>
  <update id="update" parameterType="${packageName}.entities.${domain.code.capitalize}Entity">
    <#if domain.idProperty??>
    update ${domain.tableName}
    <trim prefix="set" suffixOverrides=",">
      <#list domain.properties as property>
      <if test="${property.code} != null and ${property.code} != ''">
          ${property.column.name} = #{${property.code}, jdbcType=${property.column.type}}},
      </if>
      </#list>
    </trim>
    where ${domain.idProperty.column.name} = #{${domain.idProperty.code}, jdbcType=${domain.idProperty.column.type}}
    </#if>
  </update>
</mapper>
