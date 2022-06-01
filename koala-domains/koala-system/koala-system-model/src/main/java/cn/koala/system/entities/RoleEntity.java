package cn.koala.system.entities;

import cn.koala.system.models.Role;
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
public class RoleEntity extends BaseEntity implements Role {
  private String code;
  private String name;
  private String description;
  private List<PermissionEntity> permissions = new ArrayList<>();
}
