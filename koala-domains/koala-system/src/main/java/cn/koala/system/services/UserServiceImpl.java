package cn.koala.system.services;

import cn.koala.system.models.User;
import cn.koala.system.repositories.UserRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class UserServiceImpl extends BaseCrudService<String, User> implements UserService {
  private final UserRepository repository;
}
