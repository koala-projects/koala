package cn.koala.system.permission;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 增删改查权限
 *
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public class CrudPermission {

  private String code;

  private String name;

  private Long sortIndex;

  public static CrudPermission of(String code, String name, Long sortIndex) {
    return CrudPermission.builder()
      .code(code)
      .name(name)
      .sortIndex(sortIndex)
      .build();
  }
}
