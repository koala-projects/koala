package cn.koala.attachment.support;

import cn.koala.attachment.Attachment;
import cn.koala.attachment.AttachmentService;
import cn.koala.attachment.repository.AttachmentRepository;
import cn.koala.mybatis.AbstractMyBatisService;

/**
 * 附件服务类
 *
 * @author Koala Code Generator
 */
public class DefaultAttachmentService extends AbstractMyBatisService<Attachment, Long> implements AttachmentService {

  /**
   * 构造函数
   *
   * @param repository 仓库接口
   */
  public DefaultAttachmentService(AttachmentRepository repository) {
    super(repository);
  }
}
