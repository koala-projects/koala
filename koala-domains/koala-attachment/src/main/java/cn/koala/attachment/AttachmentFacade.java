package cn.koala.attachment;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * 附件门面
 * <p>
 * 将CRUD服务与上传下载业务逻辑组合到一起
 *
 * @author Houtaroy
 */
public interface AttachmentFacade extends AttachmentService {
  Attachment upload(MultipartFile attachment) throws Exception;

  void download(Long id, HttpServletResponse response) throws Exception;
}
