package com.umasuo.report.application.service;

import com.umasuo.report.application.dto.DeviceReportDraft;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by Davis on 17/6/16.
 */
@Component
public class DeviceReportScheduled {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(DeviceReportScheduled.class);

  /**
   * The Report application.
   */
  @Autowired
  private transient DeviceReportApplication reportApplication;

  private static long SECOND_OF_HOUR = 3600000;
  /**
   * The Rest client.
   */
  @Autowired
  private transient RestClient restClient;

  /**
   * Get last hour's report data in UCT timezone.
   * All of our data is stored in UTC timezone
   */
  @Scheduled(cron = "${scheduling.job.cron}")
  private void getHourlyReport() {
    LOG.info("Enter. system time: {}.", ZonedDateTime.now());

    long curTime = System.currentTimeMillis();
    long startTime = curTime - curTime % SECOND_OF_HOUR - SECOND_OF_HOUR;
    long endTime = startTime + SECOND_OF_HOUR;

    // get three kind of data: increase, online, total
    List<DeviceReportDraft> reportDrafts = restClient.getDeviceReport(startTime, endTime);

    reportApplication.handleHourlyReport(reportDrafts, startTime);

    LOG.info("Exit.");
  }
}
