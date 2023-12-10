package cn.koala.data.domain;

import org.springframework.data.domain.Persistable;

import java.util.Date;

/**
 * 可审计的实体接口
 *
 * @author Houtaroy
 */
public interface Auditable<U, ID> extends Persistable<ID> {

  U getCreatedBy();

  void setCreatedBy(U createdBy);

  Date getCreatedDate();

  void setCreatedDate(Date creationDate);

  U getLastModifiedBy();

  void setLastModifiedBy(U lastModifiedBy);

  Date getLastModifiedDate();

  void setLastModifiedDate(Date lastModifiedDate);

  YesNo getDeleted();

  void setDeleted(YesNo deleted);

  U getDeletedBy();

  void setDeletedBy(U deletedBy);

  Date getDeletedDate();

  void setDeletedDate(Date deletedDate);
}
