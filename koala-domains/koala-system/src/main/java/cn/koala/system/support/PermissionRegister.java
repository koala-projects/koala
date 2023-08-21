package cn.koala.system.support;

import cn.koala.boot.AbstractApplicationRunner;
import cn.koala.system.Permission;
import cn.koala.system.PermissionRegistrar;
import cn.koala.system.repositories.PermissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 权限注册器
 *
 * @author Houtaroy
 */
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
  public boolean isEnabled() {
    Assert.notNull(this.configRegistry, "ApplicationRunner配置注册器不能为空");
    return this.configRegistry.getConfigOrDefault(getName(), true);
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
