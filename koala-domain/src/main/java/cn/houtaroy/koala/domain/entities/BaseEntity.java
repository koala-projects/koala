package cn.houtaroy.koala.domain.entities;

import cn.houtaroy.koala.domain.models.Auditable;
import cn.houtaroy.koala.domain.models.Idable;
import cn.houtaroy.koala.domain.models.Sortable;
import cn.houtaroy.koala.domain.models.Stateable;
import cn.houtaroy.koala.domain.models.User;
import cn.houtaroy.koala.domain.models.YesNo;
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
  protected User createUser;
  protected LocalDateTime createTime;
  protected User lastModifyUser;
  protected LocalDateTime lastModifyTime;
  protected User deleteUser;
  protected LocalDateTime deleteTime;
}
