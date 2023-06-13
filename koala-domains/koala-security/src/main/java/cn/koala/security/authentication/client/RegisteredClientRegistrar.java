package cn.koala.security.authentication.client;

import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

/**
 * 注册客户端注册器
 *
 * @author Houtaroy
 */
public interface RegisteredClientRegistrar {
  void register(RegisteredClientRepository repository);
}
