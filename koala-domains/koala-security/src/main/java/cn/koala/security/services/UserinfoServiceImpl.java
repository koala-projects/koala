package cn.koala.security.services;

import cn.koala.security.SpringSecurityHelper;
import cn.koala.security.entities.UserDetailsImpl;
import cn.koala.security.repositories.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * 用户信息接口实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class UserinfoServiceImpl implements UserinfoService {

  protected final PasswordEncoder passwordEncoder;
  protected final UserDetailsRepository repository;

  @Override
  public UserDetails getUserinfo() {
    return Optional.ofNullable(SpringSecurityHelper.getCurrentUserDetails())
      .map(this::cleanPassword)
      .orElse(null);
  }

  protected UserDetails cleanPassword(UserDetails userDetails) {
    if (userDetails instanceof UserDetailsImpl impl) {
      impl.setPassword(null);
    }
    return userDetails;
  }

  @Override
  public void changePassword(@NonNull String password, @NonNull String newPassword) {
    UserDetails user = getUserinfo();
    Assert.notNull(user, "未登录");
    user = repository.findByUsername(user.getUsername()).orElse(null);
    Assert.notNull(user, "用户不存在");
    Assert.isTrue(passwordEncoder.matches(password, user.getPassword()), "当前密码输入错误");
    repository.changePassword(user.getUsername(), passwordEncoder.encode(newPassword));
  }
}
