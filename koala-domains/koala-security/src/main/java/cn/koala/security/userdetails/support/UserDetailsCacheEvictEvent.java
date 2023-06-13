package cn.koala.security.userdetails.support;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * 用户详情缓存失效事件
 * <p>用于支持动态切换权限等功能
 *
 * @author Houtaroy
 */
@Getter
public class UserDetailsCacheEvictEvent extends ApplicationEvent {

  private final List<String> usernames;

  public UserDetailsCacheEvictEvent(Object source, List<String> usernames) {
    super(source);
    this.usernames = usernames;
  }
}
