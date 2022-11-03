package cn.koala.system.mybatis;

import cn.koala.system.ChangePasswordDTO;
import cn.koala.system.PersonalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

/**
 * 个人服务MyBatis实现
 *
 * @author Houtaroy
 */
@Slf4j
@RequiredArgsConstructor
public class PersonalServiceImpl implements PersonalService {

  protected final UserDetailsRepository userDetailsRepository;
  protected final PasswordEncoder passwordEncoder;
  protected final PersonalRepository personalRepository;

  @Override
  public void changePassword(ChangePasswordDTO dto) {
    UserDetails userDetails = userDetailsRepository.findByUsername(dto.getUsername());
    Assert.notNull(userDetails, "用户信息异常, 请联系管理员");
    Assert.isTrue(passwordEncoder.matches(dto.getPasswordOld(), userDetails.getPassword()), "原密码不正确");
    personalRepository.changePassword(userDetails.getUsername(), passwordEncoder.encode(dto.getPasswordNew()));
  }
}
