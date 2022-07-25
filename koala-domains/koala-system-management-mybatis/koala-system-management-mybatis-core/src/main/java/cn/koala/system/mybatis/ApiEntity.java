package cn.koala.system.mybatis;

import cn.koala.system.Api;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ApiEntity extends AbstractEntity implements Api {
  private String code;
  private String name;
  private String description;
  private String url;
  private String method;
}
