package cn.koala.codegen.context;

import cn.koala.codegen.utils.CodeGenNames;

import java.util.ArrayList;
import java.util.List;

/**
 * 考拉接口代码生成上下文加工器
 *
 * @author Houtaroy
 */
public class KoalaApiCodeGenContextProcessor implements CodeGenContextProcessor {

  @Override
  public CodeGenContext process(CodeGenContext context) {
    List<DomainProperty> properties = context.get("properties");
    List<String> neglects = new ArrayList<>();
    if (context.get("systemic")) {
      neglects.add(CodeGenNames.COLUMN_SYSTEMIC);
    }
    if (context.get("auditable")) {
      neglects.addAll(CodeGenNames.COLUMN_AUDITABLE_NEGLECT);
    }
    KoalaCodeGenContext koala = context.get("koala");
    if (koala == null) {
      koala = new KoalaCodeGenContext();
    }
    koala.setParameters(
      properties.stream()
        .filter(property -> !neglects.contains(property.getName().getSnake().getSingular()))
        .map(KoalaApiParameter::from)
        .toList()
    );
    context.put("koala", koala);
    return context;
  }
}
