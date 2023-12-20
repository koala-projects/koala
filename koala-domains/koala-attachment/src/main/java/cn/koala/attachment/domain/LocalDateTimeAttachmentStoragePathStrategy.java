package cn.koala.attachment.domain;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Houtaroy
 */
public class LocalDateTimeAttachmentStoragePathStrategy implements AttachmentStoragePathStrategy {

  @Override
  public String getStoragePath(MultipartFile multipartFile) {
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
