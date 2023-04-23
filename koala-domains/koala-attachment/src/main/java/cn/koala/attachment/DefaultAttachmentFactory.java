package cn.koala.attachment;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
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
  private final AttachmentProperties properties;

  @Override
  public Attachment create(MultipartFile multipartFile) {
    return AttachmentEntity.builder()
      .originalFilename(multipartFile.getOriginalFilename())
      .contentType(multipartFile.getContentType())
      .size(computeSize(multipartFile))
      .storagePath(computeStoragePath())
      .build();
  }

  protected long computeSize(MultipartFile multipartFile) {
    return (int) Math.round((double) multipartFile.getSize() / BINARY_UNIT);
  }

  protected String computeStoragePath() {
    LocalDateTime now = LocalDateTime.now();
    String year = String.valueOf(now.getYear());
    String month = String.valueOf(now.getMonth().getValue());
    String day = String.valueOf(now.getDayOfMonth());
    String hour = String.valueOf(now.getHour());
    return FileUtils.getFile(properties.getRoot(), year, month, day, hour, UUID.randomUUID().toString()).getPath();
  }
}
