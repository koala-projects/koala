package cn.koala.attachment;

import cn.koala.attachment.storage.AttachmentStorage;
import cn.koala.persist.listener.AbstractEntityListener;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AttachmentListener extends AbstractEntityListener<Attachment> {

  protected final AttachmentStorage storage;

  @Override
  public void preDelete(Attachment entity, Attachment persist) {
    try {
      storage.remove(persist);
    } catch (Exception e) {
      LOGGER.error("附件[id=%d]删除失败".formatted(persist.getId()), e);
      throw new RuntimeException("附件删除失败, 请联系管理员");
    }
  }
}
