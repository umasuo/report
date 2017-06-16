package com.umasuo.report.application.dto;

import lombok.Data;

/**
 * Created by Davis on 17/6/16.
 */
@Data
public class UserReportView {
  /**
   * The developer id.
   */
  private String developerId;

  /**
   * The Local date.
   */
  private String localDate;

  /**
   * The Register number.
   */
  private Integer registerNumber;

  /**
   * The Online number.
   */
  private Integer onlineNumber;

  /**
   * The Total number.
   */
  private Integer totalNumber;
}
