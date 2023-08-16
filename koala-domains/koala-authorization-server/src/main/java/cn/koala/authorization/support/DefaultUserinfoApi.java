package cn.koala.authorization.support;

import cn.koala.authorization.UserinfoApi;
import cn.koala.authorization.UserinfoService;
import cn.koala.authorization.request.ChangePasswordRequest;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Houtaroy
 */
@RestController
@RequiredArgsConstructor
public class DefaultUserinfoApi implements UserinfoApi {

  private final UserinfoService service;

  @Override
  public DataResponse<UserDetails> userinfo() {
    return DataResponse.ok(service.getUserinfo());
  }

  @Override
  public Response update(ChangePasswordRequest request) {
    service.changePassword(request.getPassword(), request.getNewPassword());
    return Response.SUCCESS;
  }
}
