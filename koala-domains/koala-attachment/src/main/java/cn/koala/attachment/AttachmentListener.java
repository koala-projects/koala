package cn.koala.attachment;

import cn.koala.attachment.repository.AttachmentRepository;
import cn.koala.attachment.storage.AttachmentStorage;
import cn.koala.persist.listener.support.AbstractInheritedEntityListener;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PreRemove;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * 附件实体监听器
 *
 * @author Houtaroy
 */
@Slf4j
@Order(3100)
public class AttachmentListener extends AbstractInheritedEntityListener<Attachment> {

  protected final AttachmentRepository repository;
  protected final AttachmentStorage storage;

  public AttachmentListener(AttachmentRepository repository, AttachmentStorage storage) {
    this.repository = repository;
    this.storage = storage;
  }

  /**
   * 将存储路径设置为空, 不需要回显给前端
   *
   * @param entity 附件实体
   */
  @PostPersist
  public void postCreate(Attachment entity) {
    entity.setStoragePath(null);
  }

  /**
   * 删除附件数据前, 先删除附件存储
   *
   * @param entity 附件实体
   */
  @PreRemove
  public void preDelete(Attachment entity) {
    try {
      Optional<Attachment> persist = repository.load(entity.getId());
      Assert.isTrue(persist.isPresent(), "数据不存在");
      storage.remove(persist.get());
    } catch (Exception e) {
      throw new IllegalStateException("附件删除失败, 请联系服务管理员", e);
    }
  }
}
