package cn.koala.attachment.domain;

import cn.koala.data.domain.Auditable;
import cn.koala.data.domain.Enableable;
import cn.koala.data.domain.Sortable;
import cn.koala.data.domain.Systemic;

/**
 * 附件接口
 *
 * @author Houtaroy
 */
public interface Attachment extends Auditable<Long, Long>, Sortable, Enableable, Systemic {

  String getOriginalFilename();

  void setOriginalFilename(String originalFilename);

  String getContentType();

  void setContentType(String contentType);

  Long getSize();

  void setSize(Long size);

  String getStoragePath();

  void setStoragePath(String storagePath);

  String getStorageUri();

  void setStorageUri(String storageUrl);
}
