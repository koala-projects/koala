package cn.koala.codegen.context;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Koala Admin 代码生成上下文
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
public class KoalaAdminCodeGenContext {

  private List<KoalaAdminProperty> properties;
}
