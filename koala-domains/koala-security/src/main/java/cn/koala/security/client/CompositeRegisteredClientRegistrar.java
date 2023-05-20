package cn.koala.security.client;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import java.util.List;

/**
 * 符合注册客户端注册器
 *
 * @author Houtaroy
 */
@Order(4220)
@RequiredArgsConstructor
public class CompositeRegisteredClientRegistrar implements RegisteredClientRegistrar, ApplicationRunner {

  private final List<RegisteredClientRegistrar> registries;
  private final RegisteredClientRepository repository;

  @Override
  public void register(RegisteredClientRepository repository) {
    registries.forEach(registry -> registry.register(repository));
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    this.register(repository);
  }
}
