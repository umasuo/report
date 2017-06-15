package com.umasuo.report.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Created by Davis on 17/6/15.
 */
@Component
public class DateConfig {

  public static ZoneId zoneId;

  public static DateTimeFormatter dateTimeFormatter;

  @Value("${server.date.zone}")
  public void setZoneId(String zone) {
    zoneId = ZoneId.of(zone);
  }

  public ZoneId getZoneId() {
    return zoneId;
  }

  @Value("${server.date.formatter}")
  public void setDateTimeFormatter(String formatter) {
    dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);
  }

  public DateTimeFormatter getDateTimeFormatter() {
    return dateTimeFormatter;
  }
}
