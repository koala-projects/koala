package cn.koala.system.mybatis;

import cn.koala.system.Auditable;
import cn.koala.system.Idable;
import cn.koala.system.Sortable;
import cn.koala.system.Stateable;
import cn.koala.system.YesNo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class AbstractEntity implements Idable<String>, Sortable, Stateable, Auditable {
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

  /**
   * 如果未提供id则设置
   *
   * @param id id
   */
  @JsonIgnore
  public void setIdIfNotProvided(String id) {
    if (StringUtils.hasText(id)) {
      return;
    }
    this.id = id;
  }
}
