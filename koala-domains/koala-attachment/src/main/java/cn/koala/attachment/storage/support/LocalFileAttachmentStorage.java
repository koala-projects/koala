package cn.koala.attachment.storage.support;

import cn.koala.attachment.domain.Attachment;
import cn.koala.attachment.storage.AttachmentFactory;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 本地文件存储器
 *
 * @author Houtaroy
 */
public class LocalFileAttachmentStorage extends AbstractAttachmentStorage {

  protected final String root;

  public LocalFileAttachmentStorage(String root) {
    this(new DefaultAttachmentFactory(), root);
  }

  public LocalFileAttachmentStorage(AttachmentFactory factory, String root) {
    super(factory);
    this.root = root;
  }

  @Override
  public Attachment upload(MultipartFile multipartFile) throws Exception {
    Attachment result = this.toAttachment(multipartFile);
    File file = obtainLocalFile(result);
    FileUtils.forceMkdirParent(file);
    multipartFile.transferTo(file);
    return result;
  }

  @Override
  public InputStream download(Attachment attachment) throws Exception {
    return new FileInputStream(FileUtils.getFile(root, attachment.getStoragePath()));
  }

  @Override
  public void remove(Attachment attachment) throws Exception {
    FileUtils.forceDelete(obtainLocalFile(attachment));
  }

  private File obtainLocalFile(Attachment attachment) {
    return FileUtils.getFile(root, attachment.getStoragePath());
  }
}
