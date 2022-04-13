package cn.houtaroy.koala.component.eucalyptus.domain;

import java.util.List;

/**
 * @author Houtaroy
 */
public interface Template {

  /**
   * 获取模板名称
   *
   * @return 模板名称
   */
  String getName();

  /**
   * 生成代码
   *
   * @param domain 领域定义
   * @return 生成结果
   * @throws Exception 异常
   */
  List<GenerateResult> generate(Domain domain) throws Exception;
}
