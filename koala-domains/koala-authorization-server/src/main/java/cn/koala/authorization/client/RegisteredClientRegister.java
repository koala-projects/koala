package cn.koala.authorization.client;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import java.util.List;

/**
 * 注册客户端注册器
 *
 * @author Houtaroy
 */
@Order(3200)
@RequiredArgsConstructor
public class RegisteredClientRegister implements ApplicationRunner {

  private final List<RegisteredClientRegistrar> registrars;

  private final RegisteredClientRepository repository;

  @Override
  public void run(ApplicationArguments args) {
    registrars.stream()
      .map(RegisteredClientRegistrar::getRegisteredClient)
      .filter(client -> repository.findByClientId(client.getClientId()) == null)
      .forEach(repository::save);
  }
}
