package com.umasuo.report.application.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by umasuo on 17/7/3.
 */
@Data
public class UserReportView implements Serializable {

  private static final long serialVersionUID = 933303007662433679L;

  /**
   * date time of the data.
   */
  private Long date;

  /**
   * The increase number of the hour.
   */
  private int increaseNumber;

  /**
   * 当前的活跃用户(活跃用户的定义为当天有请求的用户，每天的活跃用户则为当天23点的活跃用户数：不同时区不一样).
   */
  private int activeNumber;

  /**
   * total user count at this time.
   */
  private int totalNumber;
}
