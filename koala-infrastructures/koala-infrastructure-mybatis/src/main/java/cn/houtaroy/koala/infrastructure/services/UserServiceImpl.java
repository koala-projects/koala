package cn.houtaroy.koala.infrastructure.services;

import cn.houtaroy.koala.domain.models.User;
import cn.houtaroy.koala.domain.services.UserService;
import cn.houtaroy.koala.infrastructure.repositories.UserRepository;
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
