package cn.koala.wechat.miniapp.registry;

import cn.koala.system.entities.UserEntity;
import cn.koala.system.repositories.UserRepository;
import cn.koala.toolkit.DateHelper;
import cn.koala.wechat.miniapp.WechatMiniAppUser;
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
public class SimpleWechatMiniAppUserRegistry implements WechatMiniAppUserRegistry {

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
    return Optional.of(wechatMiniAppUserRepository.find(Map.of("openid", openid)))
      .filter(list -> list.size() == 1)
      .map(list -> list.get(0));
  }

  protected void doRegister(WechatMiniAppUser wechatMiniAppUser) {
    UserEntity user = UserEntity.builder()
      .username(wechatMiniAppUser.getOpenid())
      .nickname("微信用户")
      .password(passwordEncoder.encode(wechatMiniAppUser.getOpenid()))
      .createdBy(-1L)
      .createdTime(DateHelper.now())
      .build();
    userRepository.create(user);
    wechatMiniAppUser.setUserId(user.getId());
    wechatMiniAppUserRepository.create(wechatMiniAppUser);
  }
}
