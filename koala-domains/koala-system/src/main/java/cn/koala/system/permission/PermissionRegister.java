package cn.koala.system.permission;

import cn.koala.boot.AbstractApplicationRunner;
import cn.koala.system.domain.Permission;
import cn.koala.system.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 权限注册器
 *
 * @author Houtaroy
 */
@Order(3100)
@Slf4j
@RequiredArgsConstructor
public class PermissionRegister extends AbstractApplicationRunner {

  private final List<PermissionRegistrar> registrars;

  private final PermissionRepository repository;

  private final Map<String, PermissionRegistrarConfig> configs;

  @Override
  public String getName() {
    return "permission-register";
  }

  @Override
  protected boolean getDefault() {
    return true;
  }

  @Override
  public void doRun(ApplicationArguments args) {
    this.registrars.forEach(this::register);
  }

  private void register(PermissionRegistrar registrar) {
    PermissionRegistrarConfig config = this.configs.getOrDefault(registrar.getCode(), new PermissionRegistrarConfig());
    if (!config.isEnabled()) {
      return;
    }
    registrar.getPermissions().forEach(permission -> {
      Optional<Permission> persist = this.repository.load(permission.getId());
      if (persist.isPresent()) {
        if (config.isOverwritten()) {
          this.repository.update(permission);
        } else {
          LOGGER.warn("权限[code={}]已存在，跳过自动注册", permission.getCode());
        }
      } else {
        this.repository.create(permission);
      }
    });
  }
}
