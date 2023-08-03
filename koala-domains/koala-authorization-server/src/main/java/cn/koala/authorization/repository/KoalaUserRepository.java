package cn.koala.authorization.repository;


import cn.koala.security.userdetails.KoalaUser;

import java.util.Optional;

/**
 * 考拉用户仓库接口
 *
 * @author Houtaroy
 */
public interface KoalaUserRepository {

  Optional<KoalaUser> findByUsername(String username);
}
