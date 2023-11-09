package cn.koala.system.model;

import cn.koala.system.api.request.UserCreateRequest;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 用户实体监听器
 *
 * @author Houtaroy
 */
@Order(1000)
@RequiredArgsConstructor
public class UserCreateListener {

  private final PasswordEncoder passwordEncoder;

  @PrePersist
  public void preAdd(User entity) {
    if (entity instanceof UserCreateRequest request) {
      request.setPassword(passwordEncoder.encode(request.getPlainPassword()));
    }
  }

  @PostPersist
  public void postAdd(User entity) {
    entity.setPassword(null);
  }
}
