package cn.houtaroy.koala.services;

import cn.houtaroy.koala.models.User;
import cn.houtaroy.koala.repositories.UserRepository;
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
