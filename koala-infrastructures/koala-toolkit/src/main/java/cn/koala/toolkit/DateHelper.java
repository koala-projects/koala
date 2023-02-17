package cn.koala.toolkit;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 日期帮助类
 *
 * @author Houtaroy
 */
public abstract class DateHelper {

  /**
   * 获取当前日期
   * <p>
   * 使用LocalDateTime获取, 转换为Date
   *
   * @return 当前日期
   */
  public static Date now() {
    return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
  }
}
