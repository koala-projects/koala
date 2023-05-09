package cn.koala.code.processors.support.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 实体属性
 *
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public class EntityProperty {
  private String name;
  private String description;
  private String type;
  private List<EntityValidation> validations;
}
