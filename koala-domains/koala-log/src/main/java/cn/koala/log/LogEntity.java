package cn.koala.log;

import cn.koala.data.domain.YesNo;
import cn.koala.log.util.LogConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 日志数据实体
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class LogEntity implements Log, Serializable {

  @Serial
  private static final long serialVersionUID = LogConstants.SERIAL_VERSION_UID;

  protected Long id;
  protected String module;
  protected String content;
  protected Long userId;
  protected String userIp;
  protected YesNo successful;
  protected String request;
  protected String response;
  protected String error;
  protected Long cost;
  protected Date logTime;

  @Override
  public boolean isNew() {
    return false;
  }
}
