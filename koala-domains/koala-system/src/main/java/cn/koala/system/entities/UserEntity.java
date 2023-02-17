package cn.koala.system.entities;

import cn.koala.system.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 用户数据实体类
 *
 * @author Houtaroy
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class UserEntity extends BaseSystemEntity implements User {
  String username;
  String password;
  String nickname;
  String avatar;
  String email;
  String mobile;
  String remark;
}
