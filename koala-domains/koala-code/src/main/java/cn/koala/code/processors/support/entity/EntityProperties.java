package cn.koala.code.processors.support.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 实体属性合集
 *
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public class EntityProperties {
  private EntityProperty id;
  private List<EntityProperty> others;
}
