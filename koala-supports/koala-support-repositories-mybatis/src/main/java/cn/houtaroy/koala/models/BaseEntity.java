package cn.houtaroy.koala.models;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public abstract class BaseEntity implements Idable<String>, Sortable, Stateable, Auditable {

  protected String id;
  protected Integer sortIndex;
  protected YesNo isSystem;
  protected YesNo isEnable;
  protected YesNo isDelete;
  protected User createUser;
  protected LocalDateTime createTime;
  protected User lastModifyUser;
  protected LocalDateTime lastModifyTime;
  protected User deleteUser;
  protected LocalDateTime deleteTime;
}
