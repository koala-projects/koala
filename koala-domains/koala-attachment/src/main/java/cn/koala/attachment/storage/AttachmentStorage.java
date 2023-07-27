package cn.koala.attachment.storage;

import cn.koala.attachment.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 附件存储器
 *
 * @author Houtaroy
 */
public interface AttachmentStorage {

  Attachment upload(MultipartFile multipartFile) throws Exception;

  InputStream download(Attachment attachment) throws Exception;

  void remove(Attachment attachment) throws Exception;
}
