package com.umasuo.report.infrastructure.enums;

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

  public String getType() {
    return this.type;
  }
}
