package cn.koala.attachment.support;

import cn.koala.attachment.Attachment;
import cn.koala.attachment.AttachmentService;
import cn.koala.attachment.repository.AttachmentRepository;
import cn.koala.mybatis.AbstractMyBatisService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 附件服务类
 *
 * @author Koala Code Generator
 */
@RequiredArgsConstructor
@Getter
public class DefaultAttachmentService extends AbstractMyBatisService<Attachment, Long> implements AttachmentService {

  protected final AttachmentRepository repository;
}
