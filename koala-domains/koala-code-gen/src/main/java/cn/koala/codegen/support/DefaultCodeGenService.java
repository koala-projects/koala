package cn.koala.codegen.support;

import cn.koala.codegen.CodeGenResult;
import cn.koala.codegen.CodeGenService;
import cn.koala.codegen.CodeGenerator;
import cn.koala.database.domain.DatabaseTable;
import cn.koala.database.service.DatabaseService;
import cn.koala.exception.BusinessException;
import cn.koala.template.domain.Template;
import cn.koala.template.service.TemplateGroupService;
import cn.koala.util.Assert;
import cn.koala.util.CompressUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.io.FileUtils;
import org.springframework.lang.NonNull;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 基于模板引擎的代码服务抽象类
 *
 * @author Houtaroy
 */
@Slf4j
@RequiredArgsConstructor
public class DefaultCodeGenService implements CodeGenService {

  private final DatabaseService databaseService;
  private final TemplateGroupService templateGroupService;
  private final CodeGenerator generator;
  private final String downloadPath;

  @Override
  public String download(Long databaseId, List<String> tableNames, Long templateGroupId) {
    try {
      File root = preview2File(preview(databaseId, tableNames, templateGroupId));
      String result = root.getName() + "." + ArchiveStreamFactory.ZIP;
      CompressUtils.compress(root, new File(downloadPath + result), ArchiveStreamFactory.ZIP);
      return result;
    } catch (Exception e) {
      throw new BusinessException("生成代码文件失败", e);
    }
  }

  private File preview2File(Map<String, List<CodeGenResult>> preview) throws IOException {
    File tempPath = new File(downloadPath + UUID.randomUUID());
    for (List<CodeGenResult> codes : preview.values()) {
      for (CodeGenResult code : codes) {
        FileUtils.write(
          new File(tempPath.getPath() + File.separator + code.getPathname()),
          code.getContent(),
          Charset.defaultCharset()
        );
      }
    }
    return tempPath;
  }

  @Override
  public Map<String, List<CodeGenResult>> preview(Long databaseId, List<String> tableNames, Long templateGroupId) {
    List<DatabaseTable> tables = databaseService.listTable(databaseId, tableNames);
    List<Template> templates = templateGroupService.listTemplate(templateGroupId);
    Assert.notEmpty(templates, "未找到指定的代码模板");
    return tables.stream().collect(Collectors.toMap(DatabaseTable::getName, table -> generate(table, templates)));
  }

  private List<CodeGenResult> generate(@NonNull DatabaseTable table, List<Template> templates) {
    return templates.stream().map(template -> generator.generate(table, template)).toList();
  }
}
