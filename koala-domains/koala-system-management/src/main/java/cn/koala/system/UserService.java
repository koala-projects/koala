package cn.koala.system;


import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Houtaroy
 */
public interface UserService extends CrudService<String, User>, UserDetailsService {
}
