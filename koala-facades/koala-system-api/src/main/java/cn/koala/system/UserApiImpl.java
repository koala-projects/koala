package cn.koala.system;

import cn.koala.system.entities.UserEntity;
import cn.koala.system.models.User;
import cn.koala.system.web.DataResponse;
import cn.koala.system.web.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Houtaroy
 * @date 2022/4/10
 */
@RestController
public class UserApiImpl implements UserApi {
  @Override
  public DataResponse<Page<User>> page(Map<String, Object> parameters, Pageable pageable) {
    return null;
  }

  @Override
  public DataResponse<User> loadById(String id) {
    return null;
  }

  @Override
  public DataResponse<User> create(UserEntity user) {
    return null;
  }

  @Override
  public Response update(String id, UserEntity user) {
    return null;
  }

  @Override
  public Response delete(String id) {
    return null;
  }
}
