package cn.koala.system;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * 设置认证信息用户转换器Runner
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class SetAuthenticationUserConverterRunner implements ApplicationRunner {

  private final ObjectProvider<AuthenticationUserConverter> converter;

  @Override
  public void run(ApplicationArguments args) {
    converter.ifAvailable(SpringSecurityHelper::setConverter);
  }
}
