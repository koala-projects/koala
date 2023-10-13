package cn.koala.persist;

import cn.koala.persist.domain.EnumAdvice;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * 增删改查方法枚举
 *
 * @author Houtaroy
 */
@Getter
public enum CrudMethod implements EnumAdvice {

  CREATE("create", 1),
  UPDATE("update", 2),
  DELETE("delete", 3);

  public static final Map<String, Class<? extends Annotation>> JPA_PRE_ANNOTATION_MAPPING = Map.of(
    CREATE.getName(), PrePersist.class,
    UPDATE.getName(), PreUpdate.class,
    DELETE.getName(), PreRemove.class
  );

  public static final Map<String, Class<? extends Annotation>> JPA_POST_ANNOTATION_MAPPING = Map.of(
    CREATE.getName(), PostPersist.class,
    UPDATE.getName(), PostUpdate.class,
    DELETE.getName(), PostRemove.class
  );

  private final String name;
  private final int value;

  CrudMethod(String name, int value) {
    this.name = name;
    this.value = value;
  }
}
