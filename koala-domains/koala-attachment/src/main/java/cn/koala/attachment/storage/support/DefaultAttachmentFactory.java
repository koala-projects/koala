package cn.koala.attachment.storage.support;

import cn.koala.attachment.Attachment;
import cn.koala.attachment.AttachmentEntity;
import cn.koala.attachment.storage.AttachmentFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 默认附件工厂类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class DefaultAttachmentFactory implements AttachmentFactory {

  private static final int BINARY_UNIT = 1024;

  @Override
  public Attachment create(MultipartFile multipartFile) {
    return AttachmentEntity.builder()
      .originalFilename(multipartFile.getOriginalFilename())
      .contentType(multipartFile.getContentType())
      .size(computeSize(multipartFile))
      .storagePath(computeStoragePath())
      .build();
  }

  /**
   * 计算附件大小
   * <p>
   * 计算结果为四舍五入后的整数, 单位kb
   *
   * @param multipartFile 上传文件
   * @return 附件大小
   */
  protected long computeSize(MultipartFile multipartFile) {
    return (int) Math.round((double) multipartFile.getSize() / BINARY_UNIT);
  }

  /**
   * 计算实际存储路径
   * <p>
   * 例如: 2023年1月12日16点30分上传的文件, 存储路径为/2023/01/12/16/{uuid}
   *
   * @return 存储路径字符串
   */
  protected String computeStoragePath() {
    LocalDateTime now = LocalDateTime.now();
    String year = String.valueOf(now.getYear());
    String month = String.valueOf(now.getMonth().getValue());
    String day = String.valueOf(now.getDayOfMonth());
    String hour = String.valueOf(now.getHour());
    return FilenameUtils.normalize(
      FileUtils.getFile(year, month, day, hour, UUID.randomUUID().toString()).getPath(),
      true
    );
  }
}
