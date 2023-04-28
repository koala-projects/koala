package cn.koala.attachment.support;

import cn.koala.attachment.Attachment;
import cn.koala.attachment.AttachmentService;
import cn.koala.attachment.repository.AttachmentRepository;
import cn.koala.attachment.storage.AttachmentStorage;
import cn.koala.mybatis.BaseMyBatisService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

/**
 * 附件服务类
 *
 * @author Koala Code Generator
 */
public class DefaultAttachmentService extends BaseMyBatisService<Attachment, Long> implements AttachmentService {

  protected final AttachmentStorage storage;

  /**
   * 构造函数
   *
   * @param repository 仓库接口
   */
  public DefaultAttachmentService(AttachmentRepository repository, AttachmentStorage attachmentStorage) {
    super(repository);
    this.storage = attachmentStorage;
  }

  @Override
  public Attachment upload(MultipartFile attachment) throws Exception {
    Attachment result = storage.upload(attachment);
    add(result);
    result.setStoragePath(null);
    return result;
  }

  @Override
  public void download(Long id, HttpServletResponse response) throws Exception {
    Attachment attachment = load(id);
    Assert.notNull(attachment, "附件不存在");
    storage.download(attachment, response);
  }
}
