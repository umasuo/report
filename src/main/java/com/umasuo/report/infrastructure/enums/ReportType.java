package com.umasuo.report.infrastructure.enums;

import com.google.common.collect.Lists;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/**
 * Created by Davis on 17/6/15.
 */
public enum ReportType {
  /**
   * Daily report type.
   */
  DAILY("daily"),
  /**
   * Weekly report type.
   */
  WEEKLY("weekly"),
  /**
   * Monthly report type.
   */
  MONTHLY("monthly"),
  /**
   * Annual report type.
   */
  ANNUAL("annual");

  /**
   * The type.
   */
  private String type;

  /**
   * Private constructor.
   *
   * @param type the type
   */
  private ReportType(String type) {
    this.type = type;
  }

  private String getType() {
    return this.type;
  }

  public static ReportType build(String type) {
    final AtomicReference<ReportType> reference = new AtomicReference<>();

    Consumer<ReportType> consumer = reportType -> {
      if (reportType.getType().equals(type)) {
        reference.set(reportType);
      }
    };

    Lists.newArrayList(ReportType.values()).stream().forEach(consumer);

    return reference.get();
  }
}
