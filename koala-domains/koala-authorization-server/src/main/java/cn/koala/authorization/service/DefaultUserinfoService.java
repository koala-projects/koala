package cn.koala.authorization.service;

import cn.koala.authorization.repository.KoalaUserRepository;
import cn.koala.security.support.SecurityHelper;
import cn.koala.security.userdetails.KoalaUser;
import cn.koala.util.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

/**
 * 用户信息接口实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class DefaultUserinfoService implements UserinfoService {

  protected final PasswordEncoder passwordEncoder;

  private final KoalaUserRepository repository;

  @Override
  public UserDetails getUserinfo() {
    return SecurityHelper.getKoalaUser();
  }

  @Override
  public void changePassword(@NonNull String password, @NonNull String newPassword) {
    UserDetails currentUser = getUserinfo();
    Assert.notNull(currentUser, "未登录");
    Optional<KoalaUser> persistentUser = this.repository.findByUsername(currentUser.getUsername());
    Assert.isTrue(persistentUser.isPresent(), "用户不存在");
    Assert.isTrue(this.passwordEncoder.matches(password, persistentUser.get().getPassword()), "旧密码码输入错误");
    this.repository.changePassword(currentUser.getUsername(), passwordEncoder.encode(newPassword));
  }
}
