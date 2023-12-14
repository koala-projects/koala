package cn.koala.codegen.context.validation;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

/**
 * 校验类
 *
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public class JakartaValidation {

  private String name;

  private Map<String, Object> parameters;

  private String message;

  private List<String> groups;
}
