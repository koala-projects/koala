package cn.koala.attachment;

import cn.koala.persist.domain.Auditable;
import cn.koala.persist.domain.Persistable;

/**
 * 附件接口
 *
 * @author Houtaroy
 */
public interface Attachment extends Persistable<Long>, Auditable<Long> {
  String getOriginalFilename();

  void setOriginalFilename(String originalFilename);

  String getContentType();

  void setContentType(String contentType);

  Long getSize();

  void setSize(Long size);

  String getStoragePath();

  void setStoragePath(String storagePath);
}
