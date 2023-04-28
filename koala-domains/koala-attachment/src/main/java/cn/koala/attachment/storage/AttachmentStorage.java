package cn.koala.attachment.storage;

import cn.koala.attachment.Attachment;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * 附件存储器
 *
 * @author Houtaroy
 */
public interface AttachmentStorage {
  Attachment upload(MultipartFile multipartFile) throws Exception;

  void download(Attachment attachment, HttpServletResponse response) throws Exception;

  void remove(Attachment attachment) throws Exception;
}
