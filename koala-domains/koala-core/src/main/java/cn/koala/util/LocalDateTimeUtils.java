package cn.koala.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * LocalDateTime工具类
 *
 * @author Houtaroy
 */
public abstract class LocalDateTimeUtils {

  public static LocalDateTime from(Date date) {
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

  public static Date toDate() {
    return toDate(LocalDateTime.now());
  }

  public static Date toDate(LocalDateTime localDateTime) {
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }

  public static LocalDateTime atEndOfDay() {
    return atEndOfDay(LocalDateTime.now());
  }

  public static LocalDateTime atEndOfDay(LocalDateTime localDateTime) {
    return localDateTime.toLocalDate().atStartOfDay().plusDays(1).minusSeconds(1);
  }
}
