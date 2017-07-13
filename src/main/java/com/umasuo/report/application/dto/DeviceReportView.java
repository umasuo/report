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
  private int increaseNumber;

  /**
   * The Online number.
   */
  private int activeNumber;

  /**
   * The Total number.
   */
  private int totalNumber;
}
