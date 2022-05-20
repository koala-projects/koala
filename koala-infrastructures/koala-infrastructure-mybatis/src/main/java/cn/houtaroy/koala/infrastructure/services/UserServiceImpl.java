package cn.houtaroy.koala.infrastructure.services;

import cn.houtaroy.koala.infrastructure.repositories.UserRepository;
import cn.koala.system.models.User;
import cn.koala.system.services.UserService;
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
