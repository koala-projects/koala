package cn.koala.system;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 用户数据实体
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class UserEntity implements User {
  private String id;
  private String username;
  private String password;
  private String nickname;
  private String avatar;
}
