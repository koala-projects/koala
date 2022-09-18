package cn.koala.template;

import cn.koala.persistence.Idable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class PersistentTemplate implements Template, Idable<String> {
  protected String id;
  protected String name;
  protected String content;
}
