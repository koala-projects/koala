package cn.koala.system;

import org.springframework.security.core.Authentication;

import java.util.function.Function;

/**
 * 认证信息用户转换器
 *
 * @author Houtaroy
 */
public interface AuthenticationUserConverter extends Function<Authentication, User> {
}
