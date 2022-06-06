package cn.koala.system.entities;

import cn.koala.system.models.Auditable;
import cn.koala.system.models.Idable;
import cn.koala.system.models.Sortable;
import cn.koala.system.models.Stateable;
import cn.koala.system.models.YesNo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class BaseEntity implements Idable<String>, Sortable, Stateable, Auditable {
  protected String id;
  protected Integer sortIndex;
  protected YesNo isSystem;
  protected YesNo isEnable;
  protected YesNo isDelete;
  protected UserEntity createUser;
  protected LocalDateTime createTime;
  protected UserEntity lastModifyUser;
  protected LocalDateTime lastModifyTime;
  protected UserEntity deleteUser;
  protected LocalDateTime deleteTime;
}
