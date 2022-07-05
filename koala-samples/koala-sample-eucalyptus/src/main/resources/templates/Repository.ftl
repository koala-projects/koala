<#assign domainCapitalizedCode = domain.code?cap_first>
package ${packageName}.repositories;

import ${packageName}.entities.${domainCapitalizedCode}Entity;

import cn.koala.mybatis.PageRepository;

/**
 * ${domain.name}存储库类
 *
 * @author Koala Eucalyptus
 */
public interface ${domainCapitalizedCode}Repository extends PageRepository<String, ${domainCapitalizedCode}Entity> {
}