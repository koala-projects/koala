package cn.koala.attachment.domain;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.InputStream;

/**
 * 附件下载结果
 * <p>
 * 用于服务接口返回
 *
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public class AttachmentDownload {

  private Attachment attachment;

  private InputStream inputStream;
}
