package cn.koala.system;

import cn.koala.web.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Houtaroy
 * @date 2022/4/10
 */
@RestController
@RequiredArgsConstructor
public class UserinfoApiImpl implements UserinfoApi {

  private final UserDetailsService service;

  @Override
  public DataResponse<UserDetails> load(Principal principal) {
    return DataResponse.ok(service.loadUserByUsername(principal.getName()));
  }
}
