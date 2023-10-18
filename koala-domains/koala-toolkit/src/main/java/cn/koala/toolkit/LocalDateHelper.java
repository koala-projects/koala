package cn.koala.toolkit;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * LocalDateTime工具类
 *
 * @author Houtaroy
 */
public abstract class LocalDateHelper {

  public static LocalDateTime from(Date date) {
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
  }
}
