package cn.koala.code.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 简易模板组
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleTemplateGroup implements TemplateGroup {
  protected String name;
  protected List<SimpleTemplate> templates;
}
