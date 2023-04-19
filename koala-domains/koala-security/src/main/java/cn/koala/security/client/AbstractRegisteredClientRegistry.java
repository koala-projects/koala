package cn.koala.security.client;

import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

/**
 * 注册客户端注册器抽象类
 *
 * @author Houtaroy
 */
public abstract class AbstractRegisteredClientRegistry implements RegisteredClientRegistry {
  @Override
  public void register(RegisteredClientRepository repository) {
    RegisteredClient client = obtainRegisteredClient();
    if (repository.findByClientId(client.getClientId()) == null) {
      repository.save(client);
    }
  }

  protected abstract RegisteredClient obtainRegisteredClient();
}
