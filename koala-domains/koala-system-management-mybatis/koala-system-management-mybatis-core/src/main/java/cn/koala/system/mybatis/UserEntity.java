package cn.koala.system.mybatis;

import cn.koala.system.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class UserEntity extends AbstractEntity implements User {
  private String username;
  private String password;
  private String name;
  private String avatar;
  private String email;
  private String phone;
  private List<RoleEntity> roles = new ArrayList<>();
}
