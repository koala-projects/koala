package cn.koala.attachment.api;

import cn.koala.attachment.domain.Attachment;
import cn.koala.attachment.domain.AttachmentEntity;
import cn.koala.attachment.service.AttachmentService;
import cn.koala.exception.BusinessException;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

  private final AttachmentService service;

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
  public DataResponse<Attachment> upload(MultipartFile multipartFile) {
    return DataResponse.ok(service.upload(multipartFile));
  }

  @Override
  public void download(Long id, HttpServletResponse response) {
    var attachmentDownload = service.download(id);
    try (
      InputStream inputStream = attachmentDownload.getInputStream();
      ServletOutputStream outputStream = response.getOutputStream()
    ) {
      var attachment = attachmentDownload.getAttachment();
      String filename = URLEncoder.encode(attachment.getOriginalFilename(), StandardCharsets.UTF_8);
      response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=%s".formatted(filename));
      response.setContentType(attachment.getContentType());
      IOUtils.copy(inputStream, outputStream);
    } catch (IOException e) {
      throw new BusinessException("文件下载失败, 请联系服务管理员", e);
    }
  }
}
