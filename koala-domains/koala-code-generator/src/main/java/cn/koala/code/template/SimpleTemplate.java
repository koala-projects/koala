package cn.koala.code.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 简易模板
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleTemplate implements Template {
  protected String name;
  protected String content;
}
