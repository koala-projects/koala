package cn.koala.system.mybatis;

import cn.koala.mybatis.AbstractUUIDCrudService;
import cn.koala.system.User;
import cn.koala.system.UserService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * 用户服务实现类
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class UserServiceImpl extends AbstractUUIDCrudService<User> implements UserService {
  protected final UserRepository repository;
}
