package cn.koala.attachment;

import cn.koala.persist.listener.AbstractEntityListener;
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
public class AttachmentListener extends AbstractEntityListener<Attachment> {

  @Override
  public void preDelete(Attachment entity, Attachment persist) {
    try {
      FileUtils.forceDelete(new File(persist.getStoragePath()));
    } catch (IOException e) {
      LOGGER.error("附件[id=%d]删除失败".formatted(persist.getId()), e);
      throw new RuntimeException("附件删除失败, 请联系管理员");
    }
  }
}
