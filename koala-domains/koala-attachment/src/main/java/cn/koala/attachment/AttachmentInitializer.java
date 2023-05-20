package cn.koala.attachment;

import cn.koala.persist.initializer.support.AbstractModuleInitializer;

/**
 * 附件模块初始化器
 *
 * @author Houtaroy
 */
public class AttachmentInitializer extends AbstractModuleInitializer {
  @Override
  public String getName() {
    return "attachment";
  }

  @Override
  public int getOrder() {
    return 4210;
  }
}
