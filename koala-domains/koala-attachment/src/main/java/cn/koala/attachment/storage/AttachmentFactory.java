package cn.koala.attachment.storage;

import cn.koala.attachment.domain.Attachment;
import org.springframework.web.multipart.MultipartFile;

/**
 * 附件工厂
 * <p>
 * 根据上传文件创建附件实体
 *
 * @author Houtaroy
 */
public interface AttachmentFactory {

  Attachment create(MultipartFile multipartFile);
}
