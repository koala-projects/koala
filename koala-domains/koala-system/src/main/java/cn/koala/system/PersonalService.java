package cn.koala.system;

/**
 * 个人服务
 *
 * @author Houtaroy
 */
public interface PersonalService {
  /**
   * 修改密码
   *
   * @param dto 修改密码数据传输对象
   */
  void changePassword(ChangePasswordDTO dto);
}
