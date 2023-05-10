package cn.koala.powerjob.worker;

import groovy.lang.GroovyShell;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import tech.powerjob.worker.core.processor.ProcessResult;
import tech.powerjob.worker.core.processor.TaskContext;
import tech.powerjob.worker.core.processor.sdk.BasicProcessor;
import tech.powerjob.worker.log.OmsLogger;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Groovy脚本任务处理器
 *
 * @author Houtaroy
 */
public class GroovyProcessor implements BasicProcessor, ApplicationContextAware {

  private static final Map<String, GroovyShell> SHELLS = new HashMap<>();

  protected ApplicationContext applicationContext;

  @Override
  public ProcessResult process(TaskContext context) throws Exception {
    OmsLogger logger = context.getOmsLogger();
    String filePathName = context.getJobParams();
    File script = new File(filePathName);
    if (!script.exists()) {
      String errorMessage = "脚本[%s]不存在".formatted(filePathName);
      logger.error(errorMessage);
      return new ProcessResult(false, errorMessage);
    }
    getShell(filePathName).evaluate(FileUtils.readFileToString(script, StandardCharsets.UTF_8));
    return new ProcessResult(true, "脚本[%s]执行成功".formatted(filePathName));
  }

  protected GroovyShell getShell(String filePathName) {
    return SHELLS.computeIfAbsent(filePathName, k -> {
      GroovyShell result = new GroovyShell();
      result.setProperty("applicationContext", applicationContext);
      return result;
    });
  }

  @Override
  public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
