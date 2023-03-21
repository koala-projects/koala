package cn.koala.persist.domain;

import java.util.Date;

/**
 * 可审计的模型
 *
 * @author Houtaroy
 */
public interface Auditable<U> {
  U getCreatedBy();

  void setCreatedBy(U createdBy);

  Date getCreatedTime();

  void setCreatedTime(Date createdTime);

  U getLastModifiedBy();

  void setLastModifiedBy(U lastModifiedBy);

  Date getLastModifiedTime();

  void setLastModifiedTime(Date lastModifiedTime);

  U getDeletedBy();

  void setDeletedBy(U deletedBy);

  Date getDeletedTime();

  void setDeletedTime(Date deletedTime);
}
