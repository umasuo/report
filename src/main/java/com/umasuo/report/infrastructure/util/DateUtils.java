package com.umasuo.report.infrastructure.util;

import com.umasuo.report.infrastructure.config.DateConfig;
import com.umasuo.report.infrastructure.enums.ReportType;

import java.time.ZonedDateTime;

/**
 * Created by Davis on 17/6/15.
 */
public final class DateUtils {

  /**
   * Instantiates a new Date utils.
   */
  private DateUtils() {
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
