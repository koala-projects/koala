package cn.koala.system.listeners;

import cn.koala.persist.support.AbstractInheritedEntityListener;
import cn.koala.system.User;
import cn.koala.system.apis.request.CreateUserRequest;
import cn.koala.system.repositories.UserRepository;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 用户监听器
 *
 * @author Houtaroy
 */
@Order(100)
public class UserListener extends AbstractInheritedEntityListener<User> {
  protected final UserRepository userRepository;
  protected final PasswordEncoder passwordEncoder;

  public UserListener(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @PrePersist
  public void preAdd(User entity) {
    if (entity instanceof CreateUserRequest request) {
      request.setPassword(passwordEncoder.encode(request.getPlainPassword()));
    }
  }

  @PostPersist
  public void postAdd(User entity) {
    entity.setPassword(null);
  }
}
