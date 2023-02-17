package cn.koala.openapi;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分页参数注解
 *
 * @author Houtaroy
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Parameter(in = ParameterIn.QUERY, description = "页码", name = "page",
  content = @Content(schema = @Schema(type = "integer", defaultValue = "0")))
@Parameter(in = ParameterIn.QUERY, description = "每页大小", name = "size",
  content = @Content(schema = @Schema(type = "integer", defaultValue = "50")))
@Parameter(in = ParameterIn.QUERY, description = "排序字段: 排序属性(,asc|desc)", name = "sort",
  array = @ArraySchema(schema = @Schema(type = "string")))
public @interface PageableAsQueryParam {
}
