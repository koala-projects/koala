package cn.koala.attachment.service;

import cn.koala.attachment.domain.Attachment;
import cn.koala.attachment.domain.AttachmentDownload;
import cn.koala.attachment.domain.AttachmentStorage;
import cn.koala.attachment.repository.AttachmentRepository;
import cn.koala.exception.BusinessException;
import cn.koala.mybatis.service.AbstractSmartService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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

  private final AttachmentStorage storage;

  @Override
  public Attachment upload(MultipartFile multipartFile) {
    try {
      Attachment attachment = storage.put(multipartFile);
      create(attachment);
      return attachment;
    } catch (Exception e) {
      throw new BusinessException("附件上传失败", e);
    }
  }

  @Override
  public AttachmentDownload download(Long id) {
    try {
      Attachment attachment = load(id);
      return AttachmentDownload.builder().attachment(attachment).inputStream(storage.get(attachment)).build();
    } catch (Exception e) {
      throw new BusinessException("附件下载失败", e);
    }
  }
}
