<#assign domainCapitalizedCode = domain.code?cap_first>
package ${packageName}.services;

import ${packageName}.entities.${domainCapitalizedCode}Entity;
import ${packageName}.repositories.${domainCapitalizedCode}Repository;

import cn.koala.system.services.BaseCrudService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * ${domain.name}服务类
 *
 * @author Koala Eucalyptus
 */
@Component
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class ${domainCapitalizedCode}Service extends BaseCrudService<String, ${domainCapitalizedCode}Entity> {
  private final ${domainCapitalizedCode}Repository repository;


}
