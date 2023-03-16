package cn.koala.code.services;

import cn.koala.code.Code;
import cn.koala.database.DatabaseTable;
import cn.koala.template.TemplateGroup;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * 代码服务接口
 *
 * @author Houtaroy
 */
public interface CodeService {
  List<Code> generate(@NonNull DatabaseTable table, @NonNull TemplateGroup templateGroup);
}
