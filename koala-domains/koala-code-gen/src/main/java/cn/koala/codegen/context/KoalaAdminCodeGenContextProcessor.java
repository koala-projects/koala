package cn.koala.codegen.context;

import cn.koala.codegen.utils.CodeGenNames;

import java.util.List;

/**
 * Koala Admin 代码生成上下文处理器
 *
 * @author Houtaroy
 */
public class KoalaAdminCodeGenContextProcessor implements CodeGenContextProcessor {

  @Override
  public CodeGenContext process(CodeGenContext context) {
    KoalaAdminCodeGenContext koalaAdmin = context.get("koalaAdmin");
    if (koalaAdmin == null) {
      koalaAdmin = new KoalaAdminCodeGenContext();
    }
    koalaAdmin.setProperties(processKoalaAdminProperties(context.get("properties")));
    context.put("koalaAdmin", koalaAdmin);
    return context;
  }

  private List<KoalaAdminProperty> processKoalaAdminProperties(List<DomainProperty> properties) {
    return properties.stream()
      .filter(property -> !CodeGenNames.COLUMN_AUDITABLE_NEGLECT.contains(property.getName().getSnake().getSingular()))
      .map(KoalaAdminProperty::from)
      .toList();
  }
}
