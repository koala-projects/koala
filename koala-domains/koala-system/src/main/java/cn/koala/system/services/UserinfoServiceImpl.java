package cn.koala.system.services;

import cn.koala.security.support.SecurityHelper;
import cn.koala.system.repositories.UserinfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

/**
 * 用户信息接口实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class UserinfoServiceImpl implements UserinfoService {

  private final UserDetailsService userDetailsService;

  protected final PasswordEncoder passwordEncoder;

  private final UserinfoRepository repository;

  @Override
  public UserDetails getUserinfo() {
    return SecurityHelper.getKoalaUser();
  }

  @Override
  public void changePassword(@NonNull String password, @NonNull String newPassword) {
    UserDetails currentUser = getUserinfo();
    Assert.notNull(currentUser, "未登录");
    UserDetails persistentUser = this.userDetailsService.loadUserByUsername(currentUser.getUsername());
    Assert.notNull(persistentUser, "用户不存在");
    Assert.isTrue(this.passwordEncoder.matches(password, persistentUser.getPassword()), "当前密码输入错误");
    this.repository.changePassword(currentUser.getUsername(), passwordEncoder.encode(newPassword));
  }
}
