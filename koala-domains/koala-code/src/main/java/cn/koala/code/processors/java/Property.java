package cn.koala.code.processors.java;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

/**
 * Java属性
 *
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public class Property {
  private String name;
  private String description;
  private String type;
  private List<Validation> validations;

  @Data
  @AllArgsConstructor
  public static class Validation {
    private String name;
    private Map<String, Object> parameters;
    private String message;
    private List<String> groups;
  }
}
