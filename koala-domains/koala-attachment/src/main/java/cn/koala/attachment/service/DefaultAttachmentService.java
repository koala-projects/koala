package cn.koala.attachment.service;

import cn.koala.attachment.domain.Attachment;
import cn.koala.attachment.repository.AttachmentRepository;
import cn.koala.mybatis.service.AbstractSmartService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 附件服务类
 *
 * @author Koala Code Generator
 */
@Getter
@RequiredArgsConstructor
public class DefaultAttachmentService extends AbstractSmartService<Long, Attachment, Long>
  implements AttachmentService {

  private final AttachmentRepository repository;
}
