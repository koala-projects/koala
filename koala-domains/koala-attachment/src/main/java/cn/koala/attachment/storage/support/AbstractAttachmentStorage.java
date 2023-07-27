package cn.koala.attachment.storage.support;

import cn.koala.attachment.Attachment;
import cn.koala.attachment.storage.AttachmentFactory;
import cn.koala.attachment.storage.AttachmentStorage;
import org.springframework.web.multipart.MultipartFile;

/**
 * 附件存储器抽象类
 *
 * @author Houtaroy
 */
public abstract class AbstractAttachmentStorage implements AttachmentStorage {

  protected final AttachmentFactory factory;

  public AbstractAttachmentStorage(AttachmentFactory factory) {
    this.factory = factory;
  }

  protected Attachment toAttachment(MultipartFile multipartFile) {
    return this.factory.create(multipartFile);
  }
}
