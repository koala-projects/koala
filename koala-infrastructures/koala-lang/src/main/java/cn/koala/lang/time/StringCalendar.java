package cn.koala.lang.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * 字符串日历
 *
 * @author Houtaroy
 */
public abstract class StringCalendar {
  public static final DateTimeFormatter DATE_TIME_FORMATTER =
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

  /**
   * 将字符串日期转换为LocalDateTime, 格式为yyyy-MM-dd HH:mm:ss
   *
   * @param date 字符串日期
   * @return LocalDateTime对象
   */
  public static LocalDateTime parse(String date) {
    return parse(date, DATE_TIME_FORMATTER);
  }

  /**
   * 将字符串日期转换为LocalDateTime
   *
   * @param date      字符串日期
   * @param formatter 格式化对象
   * @return LocalDateTime对象
   */
  public static LocalDateTime parse(String date, DateTimeFormatter formatter) {
    return LocalDateTime.parse(date, formatter);
  }
}
