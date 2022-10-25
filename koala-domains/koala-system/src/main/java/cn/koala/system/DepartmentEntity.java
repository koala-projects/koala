package cn.koala.system;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 部门数据实体
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DepartmentEntity implements Department {
  private String id;
  private String code;
  private String name;
  private String description;
  private DepartmentEntity parent;
}
