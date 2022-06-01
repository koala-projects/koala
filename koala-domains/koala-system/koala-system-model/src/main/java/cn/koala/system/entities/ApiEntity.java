package cn.koala.system.entities;

import cn.koala.system.models.Api;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
public class ApiEntity extends BaseEntity implements Api {
  private String code;
  private String name;
  private String description;
  private String url;
  private String method;
}
