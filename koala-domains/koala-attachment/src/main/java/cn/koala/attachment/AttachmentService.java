package cn.koala.attachment;

import cn.koala.persist.CrudService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 附件服务接口
 *
 * @author Houtaroy
 */
public interface AttachmentService extends CrudService<Attachment, Long> {
  Attachment upload(MultipartFile attachment) throws IOException;

  void download(Long id, HttpServletResponse response) throws IOException;
}
