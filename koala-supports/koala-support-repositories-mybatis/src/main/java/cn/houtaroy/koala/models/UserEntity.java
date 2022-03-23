package cn.houtaroy.koala.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
public class UserEntity extends BaseEntity implements User {

  private String username;
  private String password;
  private String name;
  private String avatar;
  private String email;
  private String phone;
  private List<RoleEntity> roles;

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
}
