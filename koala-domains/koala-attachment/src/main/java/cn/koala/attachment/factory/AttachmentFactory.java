package cn.koala.attachment.factory;

import cn.koala.attachment.Attachment;
import org.springframework.web.multipart.MultipartFile;

/**
 * 附件工厂
 * <p>
 * 用于将上传文件转换为附件对象
 *
 * @author Houtaroy
 */
public interface AttachmentFactory {
  Attachment create(MultipartFile multipartFile);
}
