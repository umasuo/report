package com.umasuo.report.application.service;

import com.umasuo.report.application.dto.UserReportDraft;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Schedule to pull user report.
 */
@Component
public class UserReportScheduled {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(UserReportScheduled.class);

  /**
   * The Report application.
   */
  @Autowired
  private transient UserReportApplication reportApplication;

  /**
   * Milli second build one hour.
   */
  private static final long MILLI_SECOND_OF_HOUR = 3600000;

  /**
   * The Rest client.
   */
  @Autowired
  private transient RestClient restClient;

  /**
   * Schedule task for collect report data from other service.
   */
  @Scheduled(cron = "${scheduling.job.cron}")
  public void getHourlyReportData() {
    LOG.info("Enter. system time: {}.", ZonedDateTime.now());

    long curTime = System.currentTimeMillis();
    long startTime = curTime - curTime % MILLI_SECOND_OF_HOUR - MILLI_SECOND_OF_HOUR;
    long endTime = startTime + MILLI_SECOND_OF_HOUR;

    // get three kind build data: increase, online, total
    List<UserReportDraft> reportDrafts = restClient.getUserReport(startTime, endTime);

    reportApplication.handleFetchHourlyReport(reportDrafts, startTime);

    LOG.info("Exit.");
  }
}
