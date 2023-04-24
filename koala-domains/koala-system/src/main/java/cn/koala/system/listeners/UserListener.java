package cn.koala.system.listeners;

import cn.koala.persist.listener.AbstractEntityListener;
import cn.koala.system.User;
import cn.koala.system.apis.request.CreateUserRequest;
import cn.koala.system.repositories.UserRepository;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 用户监听器
 *
 * @author Houtaroy
 */
@Order(100)
public class UserListener extends AbstractEntityListener<User> {
  protected final UserRepository userRepository;
  protected final PasswordEncoder passwordEncoder;

  public UserListener(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void preAdd(User entity) {
    Assert.isTrue(usernameIsNotDuplicate(entity), "用户账号已存在");
    if (entity instanceof CreateUserRequest request) {
      request.setPassword(passwordEncoder.encode(request.getPlainPassword()));
    }
  }

  @Override
  public void postAdd(User entity) {
    entity.setPassword(null);
  }

  protected boolean usernameIsNotDuplicate(User user) {
    return userRepository.find(Map.of("username", user.getUsername())).isEmpty();
  }
}
