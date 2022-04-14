package cn.houtaroy.koala.component.eucalyptus;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Houtaroy
 */
@Data
@AllArgsConstructor
public class SimpleTemplate implements Template {
  private String code;
  private String name;
  private List<Generator> generators;
}
