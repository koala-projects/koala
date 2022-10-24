package cn.koala.system;

import cn.koala.system.mybatis.DictionaryItemRepository;
import cn.koala.system.mybatis.DictionaryRepository;
import cn.koala.system.mybatis.MyBatisDictionaryItemService;
import cn.koala.system.mybatis.MyBatisDictionaryService;
import cn.koala.system.mybatis.MyBatisPermissionService;
import cn.koala.system.mybatis.MyBatisRoleService;
import cn.koala.system.mybatis.PermissionRepository;
import cn.koala.system.mybatis.RoleRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO: 修改类描述
 *
 * @author Houtaroy
 */
@Configuration
@MapperScan(basePackages = "cn.koala.system.mybatis")
public class SystemAutoConfig {

  /**
   * 字典服务的bean
   *
   * @param dictionaryRepository 字典存储库
   * @return 字典服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public DictionaryService dictionaryService(DictionaryRepository dictionaryRepository) {
    return new MyBatisDictionaryService(dictionaryRepository);
  }

  /**
   * 字典项服务的bean
   *
   * @param dictionaryItemRepository 字典项存储库
   * @return 字典项服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public DictionaryItemService dictionaryItemService(DictionaryItemRepository dictionaryItemRepository) {
    return new MyBatisDictionaryItemService(dictionaryItemRepository);
  }

  /**
   * 权限服务的bean
   *
   * @param permissionRepository 权限存储库
   * @return 权限服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public PermissionService permissionService(PermissionRepository permissionRepository) {
    return new MyBatisPermissionService(permissionRepository);
  }

  /**
   * 角色服务的bean
   *
   * @param roleRepository 角色存储库
   * @return 角色服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public RoleService roleService(RoleRepository roleRepository) {
    return new MyBatisRoleService(roleRepository);
  }
}
