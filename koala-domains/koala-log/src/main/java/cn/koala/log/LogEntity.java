package cn.koala.log;

import cn.koala.mybatis.YesNo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * 日志数据实体
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class LogEntity implements Log {
  protected Long id;
  protected String module;
  protected String content;
  protected Long userId;
  protected String userIp;
  protected YesNo isSucceeded;
  protected String request;
  protected String response;
  protected String error;
  protected Long cost;
  protected Date logTime;
}
