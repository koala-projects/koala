package cn.koala.system;

import cn.koala.system.mybatis.UserEntity;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Houtaroy
 * @date 2022/4/10
 */
@RequiredArgsConstructor
@RestController
public class UserApiImpl implements UserApi {
  protected final UserService userService;

  @Override
  public DataResponse<Page<User>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(userService.list(parameters, pageable));
  }

  @Override
  public DataResponse<User> loadById(String id) {
    return DataResponse.ok(userService.load(id).orElse(null));
  }

  @Override
  public DataResponse<User> create(UserEntity user) {
    userService.add(user);
    return DataResponse.ok(user);
  }

  @Override
  public Response update(String id, UserEntity user) {
    user.setId(id);
    userService.update(user);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(String id) {
    userService.delete(UserEntity.builder().id(id).build());
    return Response.SUCCESS;
  }

  @Override
  public Response resetPassword(String id) {
    userService.resetPassword(UserEntity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
