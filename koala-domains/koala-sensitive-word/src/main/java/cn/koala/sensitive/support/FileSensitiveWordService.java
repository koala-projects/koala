package cn.koala.sensitive.support;

import cn.koala.sensitive.SensitiveWordService;
import cn.koala.web.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

/**
 * 基于文件的敏感词服务类
 *
 * @author Houtaroy
 */
@Slf4j
public class FileSensitiveWordService implements SensitiveWordService {

  private final List<String> words;

  public FileSensitiveWordService(String filename) throws IOException {
    this(new File(filename));
  }

  public FileSensitiveWordService(File file) throws IOException {
    this.words = FileUtils.readLines(file, Charset.defaultCharset());
  }

  @Override
  public List<String> list() {
    return Collections.unmodifiableList(words);
  }

  @Override
  public void add(String word) {
    LOGGER.debug("[koala-sensitive-word]: 文件敏感词服务不允许新增敏感词");
    throw new BusinessException("文件敏感词服务不允许新增敏感词");
  }

  @Override
  public void delete(String word) {
    LOGGER.debug("[koala-sensitive-word]: 文件敏感词服务不允许删除敏感词");
    throw new BusinessException("文件敏感词服务不允许删除敏感词");
  }
}
