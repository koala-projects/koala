package cn.koala.attachment;

import cn.koala.persist.listener.BaseEntityListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * 附件实体监听器
 *
 * @author Houtaroy
 */
@Slf4j
public class AttachmentListener extends BaseEntityListener {

  public AttachmentListener() {
    super(Attachment.class);
  }

  @Override
  public void beforeAdd(Object entity) {

  }

  @Override
  public void afterAdd(Object entity) {

  }

  @Override
  public void beforeUpdate(Object entity, Object persist) {

  }

  @Override
  public void afterUpdate(Object entity) {

  }

  @Override
  public void beforeDelete(Object entity, Object persist) {
    if (persist instanceof Attachment attachment) {
      try {
        FileUtils.forceDelete(new File(attachment.getStoragePath()));
      } catch (IOException e) {
        LOGGER.error("附件[id=%d]删除失败".formatted(attachment.getId()), e);
        throw new RuntimeException("附件删除失败, 请联系管理员");
      }
    }
  }

  @Override
  public void afterDelete(Object entity) {

  }
}
