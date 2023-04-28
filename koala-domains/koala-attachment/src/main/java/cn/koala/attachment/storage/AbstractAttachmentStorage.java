package cn.koala.attachment.storage;

import cn.koala.attachment.Attachment;
import cn.koala.attachment.factory.AttachmentFactory;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 附件存储器抽象类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public abstract class AbstractAttachmentStorage implements AttachmentStorage {

  protected final AttachmentFactory factory;

  @Override
  public Attachment upload(MultipartFile multipartFile) throws Exception {
    Attachment result = factory.create(multipartFile);
    doUpload(multipartFile, result);
    return result;
  }

  public abstract void doUpload(MultipartFile multipartFile, Attachment attachment) throws Exception;

  @Override
  public void download(Attachment attachment, HttpServletResponse response) throws Exception {
    try (InputStream inputStream = doDownload(attachment); ServletOutputStream outputStream = response.getOutputStream()) {
      String filename = URLEncoder.encode(attachment.getOriginalFilename(), StandardCharsets.UTF_8);
      response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=%s".formatted(filename));
      response.setContentType(attachment.getContentType());
      IOUtils.copy(inputStream, outputStream);
    }
  }

  protected abstract InputStream doDownload(Attachment attachment) throws Exception;
}
