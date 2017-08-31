package com.umasuo.report.infrastructure.util;

import com.umasuo.report.infrastructure.config.DateConfig;
import com.umasuo.report.infrastructure.enums.ReportType;

import java.time.ZonedDateTime;
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
   * Get start date of the machine.
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

  /**
   * Get end date.
   *
   * @return String
   */
  public static String getEndDate() {
    ZonedDateTime zonedDateTime = ZonedDateTime.now(DateConfig.zoneId);
    return DateConfig.dateTimeFormatter.format(zonedDateTime);
  }

  /**
   * Get start date.
   *
   * @param type the ReportType
   * @return string
   */
  public static String getStartDate(ReportType type) {
    String result = "";
    ZonedDateTime zonedDateTime = ZonedDateTime.now(DateConfig.zoneId);

    switch (type) {
      case WEEKLY:
        result = DateConfig.dateTimeFormatter.format(zonedDateTime.minusDays(6L));
        break;
      case MONTHLY:
        result = DateConfig.dateTimeFormatter.format(zonedDateTime.minusDays(29L));
        break;
      case ANNUAL:
        result = DateConfig.dateTimeFormatter.format(zonedDateTime.minusDays(364L));
        break;
      default:
        break;
    }
    return result;
  }
}
