package cn.koala.system;

import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 个人服务接口实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class PersonalApiImpl implements PersonalApi {

  protected final PersonalService personalService;

  @Override
  public Response changePassword(Principal principal, ChangePasswordRequest request) {
    Assert.notNull(principal, "用户信息异常, 请联系管理员");
    personalService.changePassword(ChangePasswordDTO.from(principal, request));
    return Response.SUCCESS;
  }
}
