<#assign domainCapitalizedCode = domain.code?cap_first>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- namespace必须指向Dao接口 -->
<mapper namespace="${packageName}.repositories.${domainCapitalizedCode}Repository">
  <sql id="select${domainCapitalizedCode}">
    select
    <#list domain.properties as property>
    t.${property.columnName} as "${property.code}"<#if property_has_next>,</#if>
    </#list>
    from ${domain.tableCode} t
  </sql>
  <insert id="add" parameterType="${packageName}.entities.${domainCapitalizedCode}Entity">
    <selectKey resultType="string" order="BEFORE" keyProperty="id">
      select ifnull(#{id, jdbcType=VARCHAR},uuid())
    </selectKey>
    insert into ${domain.tableCode} (
    <#list domain.properties as property>
    t.${property.columnName}<#if property_has_next>,</#if>
    </#list>
    ) values (
      <#list domain.properties as property>
      #{${property.columnName}, jdbcType=${property.columnType}<#if property_has_next>,</#if>
      </#list>
    )
  </insert>
  <update id="delete" parameterType="${packageName}.entities.${domainCapitalizedCode}Entity">
    update ${domain.tableCode}
    set is_delete=1,
    delete_time=#{deleteTime, jdbcType=TIMESTAMP},
    delete_user_id=#{deleteUser.id, jdbcType=VARCHAR}
    where id = #{id, jdbcType=VARCHAR}
  </update>
  <select id="findAll" resultType="${packageName}.entities.${domainCapitalizedCode}Entity">
    <include refid="select${domainCapitalizedCode}"/>
    <where>
      (
      t.is_delete = 0
      <#list searchParameters as parameter>
      <if test='parameters.${parameter.code} != null and parameters.${parameter.code}!=""'>
        AND t.${parameter.columnName} = #{parameters.${parameter.code}}
      </if>
      </#list>
      )
    </where>
  </select>
  <select id="findById" resultType="${packageName}.entities.${domainCapitalizedCode}Entity">
    <include refid="select${domainCapitalizedCode}"/>
    where t.is_delete = 0 and t.id = #{id, jdbcType=VARCHAR}
  </select>
  <update id="update" parameterType="${packageName}.entities.${domainCapitalizedCode}Entity">
    update ${domain.tableCode}
    <trim prefix="set" suffixOverrides=",">
      <#list domain.properties as property>
      <if test="${property.code} != null and ${property.code} != ''">
          ${property.columnName} = #{${property.code}, jdbcType=${property.columnType}}},
      </if>
      </#list>
    </trim>
  </update>
</mapper>
