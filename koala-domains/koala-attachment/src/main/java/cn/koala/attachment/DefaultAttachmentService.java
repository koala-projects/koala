package cn.koala.attachment;

import cn.koala.attachment.repository.AttachmentRepository;
import cn.koala.mybatis.BaseMyBatisService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 附件服务类
 *
 * @author Koala Code Generator
 */
public class DefaultAttachmentService extends BaseMyBatisService<Attachment, Long> implements AttachmentService {

  protected final AttachmentFactory factory;

  /**
   * 构造函数
   *
   * @param repository 仓库接口
   */
  public DefaultAttachmentService(AttachmentRepository repository, AttachmentFactory attachmentFactory) {
    super(repository);
    this.factory = attachmentFactory;
  }

  @Override
  public Attachment upload(MultipartFile attachment) throws IOException {
    Attachment result = factory.create(attachment);
    File storage = new File(result.getStoragePath());
    FileUtils.forceMkdirParent(storage);
    attachment.transferTo(storage);
    add(result);
    result.setStoragePath(null);
    return result;
  }

  @Override
  public void download(Long id, HttpServletResponse response) throws IOException {
    Attachment attachment = load(id);
    Assert.notNull(attachment, "附件不存在");
    try (FileInputStream inputStream = new FileInputStream(attachment.getStoragePath());
         ServletOutputStream outputStream = response.getOutputStream()) {
      String filename = URLEncoder.encode(attachment.getOriginalFilename(), StandardCharsets.UTF_8);
      response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=%s".formatted(filename));
      response.setContentType(attachment.getContentType());
      IOUtils.copy(inputStream, outputStream);
    }
  }
}
