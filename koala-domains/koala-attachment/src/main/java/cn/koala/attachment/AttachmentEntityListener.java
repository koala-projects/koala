package cn.koala.attachment;

import cn.koala.attachment.repository.AttachmentRepository;
import cn.koala.attachment.storage.AttachmentStorage;
import cn.koala.exception.BusinessException;
import jakarta.persistence.PreRemove;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

/**
 * 附件实体监听器
 *
 * @author Houtaroy
 */
@Deprecated
@Slf4j
@Order(1000)
public class AttachmentEntityListener {

  private final AttachmentRepository repository;
  private final AttachmentStorage storage;

  public AttachmentEntityListener(AttachmentRepository repository, AttachmentStorage storage) {
    this.repository = repository;
    this.storage = storage;
  }

  /**
   * 删除附件数据前, 先删除附件存储
   *
   * @param entity 附件实体
   */
  @PreRemove
  public void preDelete(AttachmentEntity entity) {
    try {
      storage.remove(this.repository.load(entity.getId()).orElseThrow(() -> new BusinessException("附件不存在")));
    } catch (Exception e) {
      throw new BusinessException("附件删除失败, 请联系服务管理员", e);
    }
  }
}
