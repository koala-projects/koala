package cn.houtaroy.koala.apis;

import cn.houtaroy.koala.models.User;
import cn.houtaroy.koala.models.UserEntity;
import cn.houtaroy.koala.web.DataResponse;
import cn.houtaroy.koala.web.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Houtaroy
 * @date 2022/4/9
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
