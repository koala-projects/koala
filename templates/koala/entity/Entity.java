package #(package).entity;

#for(import: entity.imports)
import #(import);
#end
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * #(description)数据实体类
 *
 * @author Koala Code Generator
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "#(description)数据实体类")
public class #(name)Entity implements Persistable<#(entity.properties.id.type)>#for(interface: entity.interfaces), #(interface)#end  {
	
  @Schema(description = "#(entity.properties.id.description)")
  private #(entity.properties.id.type) id;
#for(property: entity.properties.others)

#for(validation: property.validations)
  @#(validation.name)(#for(parameter : validation.parameters)#(parameter.key) = #(parameter.value), #end message = "#(validation.message)", groups = {#for(group : validation.groups)#(group).class#if(!for.last), #end #end})
#end
  @Schema(description = "#(property.description)")
  private #(property.type) #(property.name);
#end
}
