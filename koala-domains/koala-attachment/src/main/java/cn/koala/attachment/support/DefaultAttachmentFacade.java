package cn.koala.attachment.support;

import cn.koala.attachment.Attachment;
import cn.koala.attachment.AttachmentFacade;
import cn.koala.attachment.AttachmentService;
import cn.koala.attachment.storage.AttachmentStorage;
import cn.koala.persist.CrudAction;
import cn.koala.persist.CrudType;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 附件管理器默认实现
 * <p>
 * 组合了默认附件服务和附件存储, 为了处理监听器切面不生效的问题
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class DefaultAttachmentFacade implements AttachmentFacade {

  protected final AttachmentService service;
  protected final AttachmentStorage storage;

  @Override
  public List<Attachment> list(Map<String, Object> parameters) {
    return service.list(parameters);
  }

  @Override
  public Page<Attachment> page(Map<String, Object> parameters, Pageable pageable) {
    return service.page(parameters, pageable);
  }

  @Override
  public Attachment load(Long id) {
    return service.load(id);
  }

  @Override
  @CrudAction(CrudType.CREATE)
  public <S extends Attachment> void create(@NonNull S entity) {
    service.create(entity);
  }

  @Override
  @CrudAction(CrudType.UPDATE)
  public <S extends Attachment> void update(@NonNull S entity) {
    service.update(entity);
  }

  @Override
  @CrudAction(CrudType.DELETE)
  public <S extends Attachment> void delete(@NonNull S entity) {
    service.delete(entity);
  }

  @Override
  public Attachment upload(MultipartFile attachment) throws Exception {
    Attachment result = storage.upload(attachment);
    service.create(result);
    return result;
  }

  @Override
  public void download(Long id, HttpServletResponse response) throws Exception {
    Attachment attachment = service.load(id);
    Assert.notNull(attachment, "附件不存在");
    storage.download(attachment, response);
  }
}
