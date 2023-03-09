package cn.koala.code.template;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.util.Assert;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 基于系统文件的模板组服务实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class FileTemplateGroupService implements TemplateGroupService {
  protected final File root;
  protected final String path;

  public FileTemplateGroupService(File root) {
    Assert.isTrue(FileUtils.isDirectory(root), "根目录[%s]异常, 请检查模板配置".formatted(root.getAbsolutePath()));
    this.root = root;
    this.path = root.getAbsolutePath() + File.separator;
  }

  @Override
  public List<TemplateGroup> list(Map<String, Object> parameters) {
    return Optional.ofNullable(root.listFiles(File::isDirectory))
      .map(files -> Arrays.stream(files)
        .map(file -> new SimpleTemplateGroup(file.getName(), null))
        .map(TemplateGroup.class::cast)
        .toList()
      )
      .orElse(List.of());
  }

  @Override
  public TemplateGroup load(String name) {
    File group = new File(path + name);
    Assert.isTrue(group.exists(), "模板组[name=%s]不存在".formatted(name));
    return new SimpleTemplateGroup(name, getTemplates(group));
  }

  public List<SimpleTemplate> getTemplates(File group) {
    return Optional.ofNullable(FileUtils.listFiles(group, null, true))
      .map(files -> files.stream().map(file -> new SimpleTemplate(file.getAbsolutePath().replace(path, ""), null)).toList())
      .orElse(List.of());
  }
}
