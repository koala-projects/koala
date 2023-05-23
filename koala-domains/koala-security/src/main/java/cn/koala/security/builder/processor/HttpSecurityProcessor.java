package cn.koala.security.builder.processor;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * 安全处理器
 * <p>
 * 提供了构建前处理方法{@link #preBuild}和构建后处理方法{@link #postBuild}
 *
 * @author Houtaroy
 */
public interface HttpSecurityProcessor {

  void preBuild(HttpSecurity http) throws Exception;

  void postBuild(HttpSecurity http) throws Exception;
}
