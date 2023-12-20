package cn.koala.attachment.domain;

import cn.koala.util.Assert;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 本地文件存储器
 *
 * @author Houtaroy
 */
public class LocalFileAttachmentStorage implements AttachmentStorage {

  private final String root;

  private final String endpoint;

  private final AttachmentStoragePathStrategy storagePathStrategy;

  public LocalFileAttachmentStorage(String root, String endpoint) {
    this(root, endpoint, new LocalDateTimeAttachmentStoragePathStrategy());
  }

  public LocalFileAttachmentStorage(String root, String endpoint, AttachmentStoragePathStrategy storagePathStrategy) {
    Assert.notNull(storagePathStrategy, "附件存储路径策略不能为空");
    this.root = root;
    this.endpoint = endpoint;
    this.storagePathStrategy = storagePathStrategy;
  }

  @Override
  public Attachment put(MultipartFile multipartFile) throws IOException {
    var storagePath = storagePathStrategy.getStoragePath(multipartFile);
    var storageFile = FileUtils.getFile(root, storagePath);
    FileUtils.forceMkdirParent(storageFile);
    multipartFile.transferTo(storageFile);
    return AttachmentEntity.builder()
      .originalFilename(multipartFile.getOriginalFilename())
      .contentType(multipartFile.getContentType())
      .size(multipartFile.getSize())
      .storagePath(storagePath)
      .storageUri(endpoint + "/" + storagePath)
      .build();
  }

  @Override
  public InputStream get(Attachment attachment) throws FileNotFoundException {
    return new FileInputStream(FileUtils.getFile(root, attachment.getStoragePath()));
  }

  @Override
  public void remove(Attachment attachment) throws IOException {
    FileUtils.forceDelete(FileUtils.getFile(root, attachment.getStoragePath()));
  }
}
