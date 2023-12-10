package cn.koala.demo.config;

import cn.koala.system.permission.SimplePermissionRegistrar;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 登录日志权限注册器
 *
 * @author Houtaroy
 */
@Component
public class LoginLogPermissionRegistrar extends SimplePermissionRegistrar {

  public LoginLogPermissionRegistrar() {
    super("login-log", "登录日志管理", 2300, null, Map.of("read", "读取"));
  }
}
