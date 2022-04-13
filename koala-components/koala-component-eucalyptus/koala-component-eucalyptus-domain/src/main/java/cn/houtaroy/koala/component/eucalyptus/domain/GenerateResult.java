package cn.houtaroy.koala.component.eucalyptus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Houtaroy
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class GenerateResult {
  protected String name;
  protected String code;
}
