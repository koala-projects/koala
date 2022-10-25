package cn.koala.system;

import cn.koala.persistence.Idable;

import java.util.List;

/**
 * 用户
 *
 * @author Houtaroy
 */
public interface User extends Idable<String> {

  /**
   * 获取用户名
   *
   * @return 用户名
   */
  String getUsername();

  /**
   * 获取密码
   *
   * @return 密码
   */
  String getPassword();

  /**
   * 设置密码
   *
   * @param password 密码
   */
  void setPassword(String password);

  /**
   * 获取昵称
   *
   * @return 昵称
   */
  String getNickname();

  /**
   * 获取头像
   *
   * @return 头像
   */
  String getAvatar();

  /**
   * 获取角色列表
   *
   * @return 角色列表
   */
  List<? extends Role> getRoles();
}
