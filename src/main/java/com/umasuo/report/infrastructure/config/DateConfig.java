package com.umasuo.report.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Date config.
 */
@Component
public class DateConfig {

  /**
   * Zone id.
   */
  public static ZoneId zoneId;

  /**
   * Date time formatter.
   */
  public static DateTimeFormatter dateTimeFormatter;

  /**
   * Set zone id.
   *
   * @param zone
   */
  @Value("${server.date.zone}")
  public void setZoneId(String zone) {
    zoneId = ZoneId.of(zone);
  }

  /**
   * Get Zone id.
   *
   * @return
   */
  public ZoneId getZoneId() {
    return zoneId;
  }

  /**
   * Set date time formatter.
   *
   * @param formatter
   */
  @Value("${server.date.formatter}")
  public void setDateTimeFormatter(String formatter) {
    dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);
  }

  /**
   * Get date time formatter.
   *
   * @return
   */
  public DateTimeFormatter getDateTimeFormatter() {
    return dateTimeFormatter;
  }
}
