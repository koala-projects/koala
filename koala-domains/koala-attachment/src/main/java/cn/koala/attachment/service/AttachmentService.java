package cn.koala.attachment.service;

import cn.koala.attachment.domain.Attachment;
import cn.koala.attachment.domain.AttachmentDownload;
import cn.koala.data.service.CrudService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 附件服务接口
 *
 * @author Houtaroy
 */
public interface AttachmentService extends CrudService<Attachment, Long> {

  Attachment upload(MultipartFile multipartFile);

  AttachmentDownload download(Long id);
}
