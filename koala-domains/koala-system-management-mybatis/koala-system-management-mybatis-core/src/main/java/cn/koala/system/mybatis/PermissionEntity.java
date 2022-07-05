package cn.koala.system.mybatis;

import cn.koala.system.Permission;
import cn.koala.system.PermissionType;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class PermissionEntity extends AbstractEntity implements Permission {
  private PermissionType type;
  private String code;
  private String name;
  private String description;
  private PermissionEntity parent;
  private List<ApiEntity> apis;
}
