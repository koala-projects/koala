package cn.koala.wechat.miniapp.repository;

import cn.koala.mybatis.repository.CrudRepository;
import cn.koala.wechat.miniapp.domain.WechatMiniAppUser;

/**
 * 微信小程序用户仓库接口
 *
 * @author Houtaroy
 */
public interface WechatMiniAppUserRepository extends CrudRepository<WechatMiniAppUser, Long> {
}
