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
 * Created by Davis on 17/6/17.
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

  private static long SECOND_OF_HOUR = 3600;

  /**
   * The Rest client.
   */
  @Autowired
  private transient RestClient restClient;

  /**
   * Schedule task for collect report data from other service.
   */
  @Scheduled(cron = "${scheduling.job.cron}")
  private void getHourlyReportData() {
    LOG.info("Enter. system time: {}.", ZonedDateTime.now());

    long curTime = System.currentTimeMillis() / 1000;
    long startTime = curTime - curTime % SECOND_OF_HOUR - SECOND_OF_HOUR;
    long endTime = startTime + SECOND_OF_HOUR;

    // get three kind of data: increase, online, total
    List<UserReportDraft> reportDrafts = restClient.getUserReport(startTime, endTime);

    reportApplication.handleFetchHourlyReport(reportDrafts, startTime);

    LOG.info("Exit.");
  }
}
