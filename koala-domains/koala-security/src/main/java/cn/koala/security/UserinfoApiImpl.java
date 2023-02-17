package cn.koala.security;

import cn.koala.web.DataResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Houtaroy
 */
@RestController
public class UserinfoApiImpl implements UserinfoApi {

  @Override
  public DataResponse<UserDetails> userinfo() {
    return DataResponse.ok(Optional.ofNullable(SecurityHelper.getCurrentUserDetails())
      .map(this::cleanPassword)
      .orElse(null));
  }

  protected UserDetails cleanPassword(UserDetails userDetails) {
    if (userDetails instanceof UserDetailsImpl impl) {
      impl.setPassword(null);
    }
    return userDetails;
  }
}
