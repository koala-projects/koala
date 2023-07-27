package cn.koala.attachment.support;

import cn.koala.attachment.Attachment;
import cn.koala.attachment.AttachmentApi;
import cn.koala.attachment.AttachmentEntity;
import cn.koala.attachment.AttachmentService;
import cn.koala.attachment.storage.AttachmentStorage;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 附件接口实现类
 *
 * @author Koala Code Generator
 */
@RequiredArgsConstructor
@RestController
@Slf4j
public class DefaultAttachmentApi implements AttachmentApi {

  protected final AttachmentService service;

  protected final AttachmentStorage storage;

  @Override
  public DataResponse<Page<Attachment>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(this.service.page(parameters, pageable));
  }

  @Override
  public DataResponse<Attachment> load(Long id) {
    return DataResponse.ok(this.service.load(id));
  }

  @Override
  public Response delete(Long id) {
    this.service.delete(AttachmentEntity.builder().id(id).build());
    return Response.SUCCESS;
  }

  @Override
  public DataResponse<Attachment> upload(MultipartFile attachment) {
    try {
      Attachment result = this.storage.upload(attachment);
      this.service.create(result);
      return DataResponse.ok(result);
    } catch (Exception e) {
      throw new IllegalStateException("文件上传失败, 请联系服务管理员", e);
    }
  }

  @Override
  public void download(Long id, HttpServletResponse response) {
    try {
      Attachment attachment = this.service.load(id);
      Assert.notNull(attachment, "附件不存在");
      try (InputStream inputStream = this.storage.download(attachment);
           ServletOutputStream outputStream = response.getOutputStream()) {
        String filename = URLEncoder.encode(attachment.getOriginalFilename(), StandardCharsets.UTF_8);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=%s".formatted(filename));
        response.setContentType(attachment.getContentType());
        IOUtils.copy(inputStream, outputStream);
      }
    } catch (Exception e) {
      throw new IllegalStateException("文件下载失败, 请联系服务管理员", e);
    }
  }
}
