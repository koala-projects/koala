package cn.koala.system.services;


import cn.koala.system.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Houtaroy
 */
public interface UserService extends CrudService<String, User>, UserDetailsService {
}
