package cn.koala.system.domain;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 用户实体监听器
 * <p>
 * 用于 加密密码 / 清除密码 / 清除不允许更新属性
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class UserEntityListener {

  private final PasswordEncoder passwordEncoder;

  @PrePersist
  public void prePersist(User entity) {
    entity.setPassword(passwordEncoder.encode(entity.getPassword()));
  }

  @PostPersist
  public void postPersist(User entity) {
    entity.setPassword(null);
  }

  @PreUpdate
  public void preUpdate(User entity) {
    entity.setUsername(null);
    entity.setPassword(null);
  }
}
