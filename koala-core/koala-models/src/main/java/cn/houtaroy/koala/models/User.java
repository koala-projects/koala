package cn.houtaroy.koala.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author Houtaroy
 */
public interface User extends Idable<String>, Sortable, Stateable, UserDetails {

  /**
   * 获取名称
   *
   * @return 名称
   */
  String getName();

  /**
   * 获取头像
   *
   * @return 头像
   */
  String getAvatar();

  /**
   * 获取邮箱
   *
   * @return 邮箱
   */
  String getEmail();

  /**
   * 获取手机号
   *
   * @return 手机号
   */
  String getPhone();

  /**
   * 获取角色列表
   *
   * @return 角色列表
   */
  List<? extends Role> getRoles();

  /**
   * 是否已启动
   *
   * @return true 是 false 否
   */
  @Override
  default boolean isEnabled() {
    return getIsEnable() == YesNo.YES;
  }

  /**
   * 获取授权列表
   *
   * @return 授权列表
   */
  @Override
  default Collection<? extends GrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> result = new ArrayList<>();
    getApis().forEach(api -> result.add(new SimpleGrantedAuthority(api.getCode())));
    return result;
  }

  /**
   * 获取接口列表
   *
   * @return 接口列表
   */
  private List<Api> getApis() {
    List<Api> result = new ArrayList<>();
    getPermissions().forEach(permission -> result.addAll(permission.getApis()));
    return result;
  }

  /**
   * 获取权限列表
   *
   * @return 权限列表
   */
  private List<Permission> getPermissions() {
    List<Permission> result = new ArrayList<>();
    Optional.ofNullable(getRoles())
      .ifPresent(roles -> roles.forEach(role -> result.addAll(role.getPermissions())));
    return result;
  }
}
