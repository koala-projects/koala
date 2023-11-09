package cn.koala.system.permission;

import cn.koala.system.model.Permission;
import org.springframework.core.Ordered;

import java.util.List;

/**
 * 权限注册登记器
 * <p>
 * 需要手动声明登记器代码, 通过配置{@link PermissionRegistrarConfig PermissionRegistrarConfig}进行开启
 *
 * @author Houtaroy
 */
public interface PermissionRegistrar extends Ordered {

  String getCode();

  List<Permission> getPermissions();
}
