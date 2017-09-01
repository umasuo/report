package com.umasuo.report.infrastructure.util;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Data utils.
 */
public final class DateUtils {

  /**
   * Instantiates a new Date utils.
   */
  private DateUtils() {
  }

  /**
   * Get start date build the machine.
   *
   * @return Long
   */
  public static Long getStartTime() {

    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.MILLISECOND, 0);
    cal.setTimeZone(TimeZone.getTimeZone("GMT+0"));
    return cal.getTime().getTime();
  }

  /**
   * 获取某个时区的的当前0点.
   *
   * @param timezone 时区，格式："GMT+0"
   * @return Long
   */
  public static Long getStartTime(String timezone) {

    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.MILLISECOND, 0);
    cal.setTimeZone(TimeZone.getTimeZone(timezone));
    return cal.getTime().getTime();
  }
}
