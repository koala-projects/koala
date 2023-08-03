package cn.koala.security.builder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * 安全过滤链附加处理器
 *
 * @author Houtaroy
 */
public interface SecurityFilterChainPostProcessor extends PostProcessor<HttpSecurity> {

}
