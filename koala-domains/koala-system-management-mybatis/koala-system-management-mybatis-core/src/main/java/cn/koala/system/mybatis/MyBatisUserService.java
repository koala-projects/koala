package cn.koala.system.mybatis;

import cn.koala.system.SystemManagementProperties;
import cn.koala.system.User;
import cn.koala.system.UserService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@SuppressWarnings("PMD")
public class MyBatisUserService extends AbstractCrudService<String, User> implements UserService {

  protected final UserRepository repository;
  protected final PasswordEncoder passwordEncoder;
  protected final SystemManagementProperties properties;

  @Override
  public void add(User user) {
    user.setPassword(passwordEncoder.encode(properties.getDefaultUserPassword()));
    super.add(user);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repository.findByUsername(username);
  }

  @Override
  public void resetPassword(User user) {
    User persistence = getPersistenceElseThrowException(user.getId());
    persistence.setPassword(passwordEncoder.encode(properties.getDefaultUserPassword()));
    repository.update(persistence);
  }
}
