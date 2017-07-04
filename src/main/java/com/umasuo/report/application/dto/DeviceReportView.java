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
  private Long date;

  /**
   * The Register number.
   */
  private Integer increaseNumber;

  /**
   * The Online number.
   */
  private Integer activeNumber;

  /**
   * The Total number.
   */
  private Integer totalNumber;
}
