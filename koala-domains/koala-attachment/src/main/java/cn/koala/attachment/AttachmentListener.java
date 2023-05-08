package cn.koala.attachment;

import cn.koala.attachment.repository.AttachmentRepository;
import cn.koala.attachment.storage.AttachmentStorage;
import cn.koala.persist.listener.support.AbstractInheritedEntityListener;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PreRemove;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * 附件实体监听器
 *
 * @author Houtaroy
 */
@Slf4j
@RequiredArgsConstructor
public class AttachmentListener extends AbstractInheritedEntityListener<Attachment> {

  protected final AttachmentRepository repository;
  protected final AttachmentStorage storage;

  @PostPersist
  public void postCreate(Attachment entity) {
    entity.setStoragePath(null);
  }

  @PreRemove
  public void preDelete(Attachment entity) {
    try {
      Optional<Attachment> persist = repository.load(entity.getId());
      Assert.isTrue(persist.isPresent(), "数据不存在");
      storage.remove(persist.get());
    } catch (Exception e) {
      LOGGER.error("附件[id=%d]删除失败".formatted(entity.getId()), e);
      throw new RuntimeException("附件删除失败, 请联系管理员");
    }
  }
}
