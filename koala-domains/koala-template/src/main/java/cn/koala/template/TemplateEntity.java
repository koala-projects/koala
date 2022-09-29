package cn.koala.template;

import cn.koala.persistence.Idable;
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
public class TemplateEntity extends StringTemplate implements Idable<String> {
  protected String id;
  protected String name;
  protected String content;
}
