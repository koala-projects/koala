package cn.koala.codegen.support.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

/**
 * 实体校验类
 *
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public class EntityValidation {

  private String name;
  private Map<String, Object> parameters;
  private String message;
  private List<String> groups;
}
