package cn.koala.code.processors.support.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Set;

/**
 * 实体类上下文
 *
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public class EntityContext {
  private Set<String> imports;
  private Set<String> interfaces;
  private EntityProperties properties;
}
