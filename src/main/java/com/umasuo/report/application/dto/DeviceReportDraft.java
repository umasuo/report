package com.umasuo.report.application.dto;

import lombok.Data;

/**
 * Created by Davis on 17/6/16.
 */
@Data
public class DeviceReportDraft {

  /**
   * The developer id.
   */
  private String developerId;

  /**
   * The Device definition id.
   */
  private String deviceDefinitionId;

  /**
   * 当前小时的起始时间戳.
   */
  private Long startTime;

  /**
   * The increase number.
   */
  private Integer increaseNumber;

  /**
   * 当前的活跃用户(活跃用户的定义为当天有请求的用户，每天的活跃用户则为当天23点的活跃用户数：不同时区不一样).
   */
  private Integer activeNumber;

  /**
   * total number of this hour.
   */
  private Integer totalNumber;

}
