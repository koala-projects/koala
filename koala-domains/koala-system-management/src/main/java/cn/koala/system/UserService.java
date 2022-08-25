package cn.koala.system;


import cn.koala.persistence.CrudService;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Houtaroy
 */
public interface UserService extends CrudService<String, User>, UserDetailsService {

  /**
   * 重置用户密码为默认值
   * <p>
   * 用户默认密码配置请参照{@link SystemManagementProperties SystemManagementProperties}
   *
   * @param user 用户
   */
  void resetPassword(User user);
}
