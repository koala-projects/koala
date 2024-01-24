package #(package).entity;

#if(abstract)
import cn.koala.mybatis.domain.AbstractEntity;
#else
#for(import: imports)
#(import)
#end
#end
import cn.koala.validation.group.Create;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
#if(abstract)
import lombok.EqualsAndHashCode;
#end
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * #(description)数据实体类
 *
 * @author Koala Code Gen
 */
@Data
#if(abstract)
@EqualsAndHashCode(callSuper = true)
#end
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "#(description)实体")
#if(abstract)
public class #(name.pascal.singular)Entity extends AbstractEntity<Long, #(id.type.java)> implements Serializable {
#else
public class #(name.pascal.singular)Entity implements#for(implement: implements) #(implement),#end  Serializable {
#end

  @Serial
  private static final long serialVersionUID = 2024_01_00L;
#if(!abstract)

  @Schema(description = "#(id.description)")
  private #(id.type.java) id;
#end
#for(property: koala.properties)

  @Schema(description = "#(property.description)")
#for(validation: property.validations)
  #(validation)
#end
  private #(property.type) #(property.name);
#end
}
