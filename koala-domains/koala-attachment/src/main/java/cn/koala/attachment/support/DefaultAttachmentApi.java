package cn.koala.attachment.support;

import cn.koala.attachment.Attachment;
import cn.koala.attachment.AttachmentApi;
import cn.koala.attachment.AttachmentEntity;
import cn.koala.attachment.AttachmentService;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

  @Override
  public DataResponse<Page<Attachment>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.page(parameters, pageable));
  }

  @Override
  public DataResponse<Attachment> load(Long id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  public DataResponse<Attachment> add(AttachmentEntity entity) {
    service.add(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(Long id, AttachmentEntity entity) {
    entity.setIdIfAbsent(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(Long id) {
    service.delete(AttachmentEntity.builder().id(id).build());
    return Response.SUCCESS;
  }

  @Override
  public DataResponse<Attachment> upload(MultipartFile attachment) {
    try {
      return DataResponse.ok(service.upload(attachment));
    } catch (Exception e) {
      LOGGER.error("文件上传失败", e);
      throw new IllegalStateException("文件上传失败, 请联系服务器管理员");
    }
  }

  @Override
  public void download(Long id, HttpServletResponse response) {
    try {
      service.download(id, response);
    } catch (Exception e) {
      LOGGER.error("文件下载失败", e);
      throw new RuntimeException("文件下载失败, 请联系服务器管理员");
    }
  }
}
