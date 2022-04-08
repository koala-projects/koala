package cn.houtaroy.koala.apis;

import cn.houtaroy.koala.models.User;
import cn.houtaroy.koala.web.DataResponse;
import cn.houtaroy.koala.web.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Houtaroy
 */
@RestController
public class UserApiImpl implements UserApi {
  @Override
  public DataResponse<Page<User>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(new PageImpl<>(List.of(), pageable, 0));
  }

  @Override
  public DataResponse<User> loadById(String id) {
    return null;
  }

  @Override
  public DataResponse<User> create(User user) {
    return null;
  }

  @Override
  public Response update(String id, User user) {
    return null;
  }

  @Override
  public Response delete(String id) {
    return null;
  }
}
