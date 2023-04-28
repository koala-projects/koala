package cn.koala.attachment.storage;

import cn.koala.attachment.Attachment;
import cn.koala.attachment.factory.AttachmentFactory;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 本地文件存储器
 *
 * @author Houtaroy
 */
public class LocalAttachmentStorage extends AbstractAttachmentStorage {

  protected final String root;

  public LocalAttachmentStorage(AttachmentFactory factory, String root) {
    super(factory);
    this.root = root;
  }

  @Override
  public void doUpload(MultipartFile multipartFile, Attachment attachment) throws IOException {
    File storage = computeLocalAttachment(attachment);
    FileUtils.forceMkdirParent(storage);
    multipartFile.transferTo(storage);
  }

  @Override
  protected FileInputStream doDownload(Attachment attachment) throws FileNotFoundException {
    return new FileInputStream(computeLocalAttachment(attachment));
  }

  public File computeLocalAttachment(Attachment attachment) {
    return FileUtils.getFile(root, attachment.getStoragePath());
  }
}
