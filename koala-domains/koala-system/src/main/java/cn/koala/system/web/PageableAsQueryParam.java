package cn.koala.system.web;

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
 * @author Houtaroy
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Parameter(in = ParameterIn.QUERY, description = "页码", name = "pageNumber",
  content = @Content(schema = @Schema(type = "integer", defaultValue = "0")))
@Parameter(in = ParameterIn.QUERY, description = "每页大小", name = "pageSize",
  content = @Content(schema = @Schema(type = "integer", defaultValue = "50")))
@Parameter(in = ParameterIn.QUERY, description = "排序字段: 排序属性(,asc|desc)", name = "sort",
  content = @Content(array = @ArraySchema(schema = @Schema(type = "string", defaultValue = "id,desc"))))
public @interface PageableAsQueryParam {
}
