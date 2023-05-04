package cn.koala.attachment;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * 附件门面
 *
 * @author Houtaroy
 */
public interface AttachmentFacade extends AttachmentService {
  Attachment upload(MultipartFile attachment) throws Exception;

  void download(Long id, HttpServletResponse response) throws Exception;
}
