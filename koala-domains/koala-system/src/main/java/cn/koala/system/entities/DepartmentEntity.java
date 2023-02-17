package cn.koala.system.entities;

import cn.koala.system.Department;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 部门数据实体类
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DepartmentEntity extends BaseSystemEntity implements Department {
  protected String name;
  protected String remark;
  protected Long parentId;
}
