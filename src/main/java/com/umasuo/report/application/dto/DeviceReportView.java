package com.umasuo.report.application.dto;

import lombok.Data;

/**
 * Created by Davis on 17/6/15.
 */
@Data
public class DeviceReportView {
  /**
   * The Device definition id.
   */
  private String deviceDefinitionId;

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
