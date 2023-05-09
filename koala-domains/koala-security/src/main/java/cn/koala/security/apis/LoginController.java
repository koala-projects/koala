package cn.koala.security.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 登录拦截器
 * <p>
 * 用于自定义登录页
 *
 * @author Houtaroy
 */
@Controller
@RequiredArgsConstructor
public class LoginController {
  @GetMapping("/login")
  public String login() {
    return "login";
  }
}
