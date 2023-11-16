package cn.koala.wechat.miniapp.support;

import cn.koala.system.model.UserEntity;
import cn.koala.system.repository.UserRepository;
import cn.koala.util.LocalDateTimeUtils;
import cn.koala.wechat.miniapp.WechatMiniAppUser;
import cn.koala.wechat.miniapp.WechatMiniAppUserRegistrar;
import cn.koala.wechat.miniapp.repository.WechatMiniAppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.Optional;

/**
 * 简易微信小程序用户注册器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class DefaultWechatMiniAppUserRegistrar implements WechatMiniAppUserRegistrar {

  protected final PasswordEncoder passwordEncoder;
  protected final UserRepository userRepository;
  protected final WechatMiniAppUserRepository wechatMiniAppUserRepository;

  @Override
  public void register(WechatMiniAppUser wechatMiniAppUser) {
    Assert.hasLength(wechatMiniAppUser.getOpenid(), "获取openid失败");
    WechatMiniAppUser miniAppUser = loadByOpenId(wechatMiniAppUser.getOpenid()).orElse(wechatMiniAppUser);
    if (miniAppUser.getUserId() == null) {
      doRegister(miniAppUser);
    }
  }

  protected Optional<WechatMiniAppUser> loadByOpenId(String openid) {
    return Optional.of(wechatMiniAppUserRepository.list(Map.of("openid", openid)))
      .filter(list -> list.size() == 1)
      .map(list -> list.get(0));
  }

  protected void doRegister(WechatMiniAppUser wechatMiniAppUser) {
    UserEntity user = UserEntity.builder()
      .username(wechatMiniAppUser.getOpenid())
      .nickname("微信用户")
      .password(passwordEncoder.encode(wechatMiniAppUser.getOpenid()))
      .createdBy(-1L)
      .createdTime(LocalDateTimeUtils.toDate())
      .build();
    userRepository.create(user);
    wechatMiniAppUser.setUserId(user.getId());
    wechatMiniAppUserRepository.create(wechatMiniAppUser);
  }
}
