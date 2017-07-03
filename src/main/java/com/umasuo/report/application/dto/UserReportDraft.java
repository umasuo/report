package com.umasuo.report.application.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Davis on 17/6/16.
 */
@Data
public class UserReportDraft implements Serializable {

  private static final long serialVersionUID = 3014600617785980921L;
  /**
   * The developer id.
   */
  private String developerId;

  /**
   * Start time of this hour.
   */
  private Long startTime;

  /**
   * The increase number of the hour.
   */
  private Integer increaseNumber;

  /**
   * 当前的活跃用户(活跃用户的定义为当天有请求的用户，每天的活跃用户则为当天23点的活跃用户数：不同时区不一样).
   */
  private Integer activeNumber;

  /**
   * total user count at this time.
   */
  private Integer totalNumber;
}
