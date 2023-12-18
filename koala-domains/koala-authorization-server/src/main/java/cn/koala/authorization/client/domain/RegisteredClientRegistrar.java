package cn.koala.authorization.client.domain;

import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

/**
 * 注册客户端登记器
 *
 * @author Houtaroy
 */
@FunctionalInterface
public interface RegisteredClientRegistrar {

  RegisteredClient getRegisteredClient();
}
