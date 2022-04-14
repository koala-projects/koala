package ${options.package};

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
* @author Koala Eucalyptus
*/
@Data
@NoArgsConstructor
@Schema(description = "${domain.description}")
@SuperBuilder(toBuilder = true)
public class ${domain.name} {

<#list domain.properties as property>
  @Schema(description = "${property.description}")
  private ${property.type} ${property.name};
</#list>
}