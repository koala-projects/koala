package cn.koala.attachment.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 附件存储器
 *
 * @author Houtaroy
 */
public interface AttachmentStorage {

  Attachment put(MultipartFile multipartFile) throws Exception;

  InputStream get(Attachment attachment) throws Exception;

  void remove(Attachment attachment) throws Exception;
}
