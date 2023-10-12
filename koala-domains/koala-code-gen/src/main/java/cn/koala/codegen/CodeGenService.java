package cn.koala.codegen;

import java.util.List;
import java.util.Map;

/**
 * 代码服务接口
 *
 * @author Houtaroy
 */
public interface CodeGenService {

  String download(Long databaseId, List<String> tableNames, Long templateGroupId);

  Map<String, List<CodeGenResult>> preview(Long databaseId, List<String> tableNames, Long templateGroupId);
}
