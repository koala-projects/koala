package #(package).entity;

#if(entity.isAbstract)
import cn.koala.mybatis.AbstractEntity;
#else
import cn.koala.persist.domain.Persistable;
#end
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
#if(entity.isAbstract)
import lombok.EqualsAndHashCode;
#end
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;



/**
 * #(description)数据实体类
 *
 * @author Koala Code Gen
 */
@Data
#if(entity.isAbstract)
@EqualsAndHashCode(callSuper = true)
#end
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "#(description)数据实体类")
public class #(name.pascal.singular)Entity#if(entity.isAbstract) extends AbstractEntity#else  implements Persistable#end<#(id.type.java)> {
#if(!entity.isAbstract)

  @Schema(description = "#(id.description)")
  private #(id.type.java) id;
#end
#for(property: properties)
  #if(entity.isAbstract)
    #if(!entity.abstractIgnoredPropertyNames.contains(property.name.camel.singular))

      #if(entity.validations.containsKey(property.name.camel.singular))
        #for(validation: entity.validations.get(property.name.camel.singular))
  @#(validation.name)(#for(parameter : validation.parameters)#(parameter.key) = #(parameter.value), #end message = "#(validation.message)", groups = {#for(group : validation.groups)#(group).class#if(!for.last), #end #end})
        #end
      #end
  @Schema(description = "#(property.description)")
  private #(property.type.java) #(property.name.camel.singular);
  
    #end
  #else
	#if(entity.validations.containsKey(property.name.camel.singular))
        #for(validation: entity.validations.get(property.name.camel.singular))
  @#(validation.name)(#for(parameter : validation.parameters)#(parameter.key) = #(parameter.value), #end message = "#(validation.message)", groups = {#for(group : validation.groups)#(group).class#if(!for.last), #end #end})
        #end
      #end
  @Schema(description = "#(property.description)")
  private #(property.type.java) #(property.name.camel.singular);
  
  #end
#end
}
