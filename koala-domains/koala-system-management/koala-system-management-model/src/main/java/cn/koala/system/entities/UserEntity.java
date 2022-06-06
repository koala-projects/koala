package cn.koala.system.entities;

import cn.koala.system.models.User;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class UserEntity extends BaseEntity implements User {
  private String username;
  private String password;
  private String name;
  private String avatar;
  private String email;
  private String phone;
  private List<RoleEntity> roles = new ArrayList<>();
}
