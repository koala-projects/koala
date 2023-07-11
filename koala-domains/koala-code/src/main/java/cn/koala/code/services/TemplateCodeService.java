package cn.koala.code.services;

import cn.koala.code.Code;
import cn.koala.code.SimpleCode;
import cn.koala.code.processors.ContextProcessor;
import cn.koala.database.DatabaseTable;
import cn.koala.template.Template;
import cn.koala.template.TemplateRenderer;
import cn.koala.toolkit.CompressHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

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
public class TemplateCodeService implements CodeService {

  private final TemplateRenderer renderer;
  private final ContextProcessor processor;
  private final String downloadPath;

  @Override
  public Map<String, List<Code>> preview(List<DatabaseTable> tables, List<Template> templates) {
    Assert.notEmpty(tables, "未找到数据表");
    Assert.notEmpty(templates, "未找到模板内容");
    return tables.stream().collect(Collectors.toMap(DatabaseTable::getName, table -> generate(table, templates)));
  }

  private List<Code> generate(@NonNull DatabaseTable table, List<Template> templates) {
    Map<String, Object> context = processor.process(table);
    return templates.stream().map(template -> generate(template, context)).toList();
  }

  private Code generate(@NonNull Template template, Map<String, Object> context) {
    return new SimpleCode(
      FilenameUtils.getPath(template.getName()) + context.get("name") + FilenameUtils.getName(template.getName()),
      renderer.render(template, context)
    );
  }

  @Override
  public String download(List<DatabaseTable> tables, List<Template> templates) {
    try {
      File root = preview2File(preview(tables, templates));
      String result = root.getName() + "." + ArchiveStreamFactory.ZIP;
      CompressHelper.compress(root, new File(downloadPath + result), ArchiveStreamFactory.ZIP);
      return result;
    } catch (Exception e) {
      throw new IllegalStateException("生成代码文件失败", e);
    }
  }

  private File preview2File(Map<String, List<Code>> preview) throws IOException {
    File root = new File(downloadPath + UUID.randomUUID());
    for (List<Code> codes : preview.values()) {
      for (Code code : codes) {
        FileUtils.write(
          new File(root.getPath() + File.separator + code.getName()),
          code.getContent(),
          Charset.defaultCharset()
        );
      }
    }
    return root;
  }
}
