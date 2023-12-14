package cn.koala.codegen.context;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
public class KoalaCodeGenContext {

  private List<KoalaApiParameter> parameters;

  private List<KoalaEntityProperty> properties;
}
