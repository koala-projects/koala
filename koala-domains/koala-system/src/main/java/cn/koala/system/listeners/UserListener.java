package cn.koala.system.listeners;

import cn.koala.persist.listener.BaseEntityListener;
import cn.koala.system.User;
import cn.koala.system.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 用户监听器
 *
 * @author Houtaroy
 */
public class UserListener extends BaseEntityListener {
  protected final UserRepository userRepository;
  protected final PasswordEncoder passwordEncoder;

  public UserListener(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    super(User.class);
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void beforeAdd(Object entity) {
    if (entity instanceof User user) {
      Assert.isTrue(usernameIsNotDuplicate(user), "用户账号已存在");
      user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
  }

  @Override
  public void afterAdd(Object entity) {

  }

  @Override
  public void beforeUpdate(Object entity, Object persist) {

  }

  @Override
  public void afterUpdate(Object entity) {

  }

  @Override
  public void beforeDelete(Object entity, Object persist) {

  }

  @Override
  public void afterDelete(Object entity) {

  }

  protected boolean usernameIsNotDuplicate(User user) {
    return userRepository.find(Map.of("username", user.getUsername())).isEmpty();
  }
}
