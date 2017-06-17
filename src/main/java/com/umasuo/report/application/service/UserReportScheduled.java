package com.umasuo.report.application.service;

import com.umasuo.report.application.dto.UserReportView;
import com.umasuo.report.infrastructure.config.DateConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
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

  /**
   * The Rest client.
   */
  @Autowired
  private transient RestClient restClient;

  /**
   * Scheduled task.
   */
  @Scheduled(cron = "${scheduling.job.cron}", zone = "${server.date.zone}")
  private void getYesterdayReport() {
    LOG.info("Enter. system time: {}.", ZonedDateTime.now());

    ZonedDateTime now = ZonedDateTime.now(DateConfig.zoneId);

    long startDateTime = now.minusDays(1L).truncatedTo(ChronoUnit.DAYS).toInstant().toEpochMilli();
    long endDateTime = now.truncatedTo(ChronoUnit.DAYS).toInstant().toEpochMilli();

    List<UserReportView> reportDrafts =
        restClient.getUserReport(startDateTime, endDateTime);

    reportApplication.handleYesterdayReport(reportDrafts);

    LOG.info("Exit.");
  }
}
