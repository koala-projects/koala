package ${packageName}.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * ${domain.name}数据实体类
 *
 * @author Koala Eucalyptus
 */
@Data
@NoArgsConstructor
@Schema(description = "${domain.name}数据实体类")
@SuperBuilder(toBuilder = true)
public class ${domain.code.capitalize}Entity {
<#list domain.properties as property>
  @Schema(description = "${property.name}")
  private ${property.type} ${property.code};
</#list>
}