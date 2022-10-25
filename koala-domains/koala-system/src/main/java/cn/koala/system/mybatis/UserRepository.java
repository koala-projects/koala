package cn.koala.system.mybatis;

import cn.koala.mybatis.PageRepository;
import cn.koala.system.User;

/**
 * 用户存储库
 *
 * @author Houtaroy
 */
public interface UserRepository extends PageRepository<String, User> {
}
