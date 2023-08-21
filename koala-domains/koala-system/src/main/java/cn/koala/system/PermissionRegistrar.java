package cn.koala.system;

import org.springframework.core.Ordered;

import java.util.List;

/**
 * 权限注册登记器
 * <p>
 * 需要手动声明登记器代码, 通过配置{@link cn.koala.system.support.PermissionRegistrarConfig PermissionRegistrarConfig}进行开启
 *
 * @author Houtaroy
 */
public interface PermissionRegistrar extends Ordered {

  String getCode();

  List<Permission> getPermissions();
}
