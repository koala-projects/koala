package cn.koala.attachment.domain;

import org.springframework.web.multipart.MultipartFile;

/**
 * 附件存储路径策略
 *
 * @author Houtaroy
 */
@FunctionalInterface
public interface AttachmentStoragePathStrategy {

  /**
   * 获取附件存储路径
   *
   * @param multipartFile 附件
   * @return 存储路径
   */
  String getStoragePath(MultipartFile multipartFile);
}
