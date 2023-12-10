package cn.koala.persist.domain;

import java.util.Date;

/**
 * 可审计的模型
 *
 * @author Houtaroy
 */
@Deprecated
public interface Auditable<ID> {
  ID getCreatedBy();

  void setCreatedBy(ID createdBy);

  Date getCreatedTime();

  void setCreatedTime(Date createdTime);

  ID getLastModifiedBy();

  void setLastModifiedBy(ID lastModifiedBy);

  Date getLastModifiedTime();

  void setLastModifiedTime(Date lastModifiedTime);

  ID getDeletedBy();

  void setDeletedBy(ID deletedBy);

  Date getDeletedTime();

  void setDeletedTime(Date deletedTime);
}
