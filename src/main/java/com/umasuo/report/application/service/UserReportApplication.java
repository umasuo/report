package com.umasuo.report.application.service;

import com.google.common.collect.Lists;
import com.umasuo.report.application.dto.UserReportView;
import com.umasuo.report.application.dto.mapper.UserReportMapper;
import com.umasuo.report.domain.model.UserReport;
import com.umasuo.report.domain.service.UserReportService;
import com.umasuo.report.infrastructure.config.DateConfig;
import com.umasuo.report.infrastructure.enums.ReportType;
import com.umasuo.report.infrastructure.util.DateUtils;
import com.umasuo.report.infrastructure.validator.DateValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Created by Davis on 17/6/16.
 */
@Service
public class UserReportApplication {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(UserReportApplication.class);

  /**
   * The Service.
   */
  @Autowired
  private UserReportService service;

  /**
   * The Rest client.
   */
  @Autowired
  private transient RestClient restClient;

  /**
   * Gets report by period.
   *
   * @param developerId the developer id
   * @param startDate the start date
   * @param endDate the end date
   * @return the report by period
   */
  public List<UserReportView> getReportByPeriod(String developerId, String startDate,
      String endDate) {
    LOG.debug("Enter. developerId: {}, startDate: {}, endDate: {}.",
        developerId, startDate, endDate);

    DateValidator.validatePattern(startDate);
    DateValidator.validatePattern(endDate);
    DateValidator.validatePeriod(startDate, endDate);

    List<UserReport> reports = service.getReportByDate(developerId, startDate, endDate);

    List<UserReportView> result = UserReportMapper.toModel(reports);

    LOG.debug("Exit. device report size: {}.", result.size());

    return result;
  }

  /**
   * Gets report by type.
   *
   * @param developerId the developer id
   * @param reportType the report type
   * @return the report by type
   */
  public List<UserReportView> getReportByType(String developerId, String reportType) {
    LOG.debug("Enter. reportType: {}.", reportType);
    ReportType type = ReportType.build(reportType);

    List<UserReportView> result = Lists.newArrayList();
    if (type.equals(ReportType.DAILY)) {
      result = getRealTimeReport(developerId);
    } else {
      result = getStatisticsReport(developerId, type);
    }

    LOG.debug("Exit. device report size: {}.", result.size());
    return result;
  }

  /**
   * Get real time report.
   *
   * @param developerId the developer id
   * @return list of DeviceReportView
   */
  private List<UserReportView> getRealTimeReport(String developerId) {
    LOG.debug("Enter. developerId: {}.");

    long startDate = ZonedDateTime.now(DateConfig.zoneId)
        .truncatedTo(ChronoUnit.DAYS).toInstant().toEpochMilli();

    UserReportView result = restClient.getRealTimeUserReport(startDate, developerId);

    LOG.debug("Exit. user report: {}.", result);
    return Lists.newArrayList(result);
  }

  /**
   * Get statistics report.
   *
   * @param developerId the developer id
   * @param type the report date type
   * @return list of DeviceReportView
   */
  private List<UserReportView> getStatisticsReport(String developerId, ReportType type) {
    LOG.debug("Enter. reportType: {}.", type);

    String startDate = DateUtils.getStartDate(type);
    String endDate = DateUtils.getEndDate();

    List<UserReport> reports = service.getReportByDate(developerId, startDate, endDate);

    List<UserReportView> result = UserReportMapper.toModel(reports);

    LOG.debug("Exit. user report size: {}.", result.size());

    return result;
  }

  /**
   * Handle yesterday report.
   *
   * @param reportDrafts the report drafts
   */
  public void handleYesterdayReport(List<UserReportView> reportDrafts) {
    LOG.debug("Enter. report size: {}.", reportDrafts.size());

    String yesterdayDate = DateConfig.dateTimeFormatter
        .format(ZonedDateTime.now(DateConfig.zoneId).minusDays(1L));

    List<UserReport> reports = UserReportMapper.toEntity(reportDrafts, yesterdayDate);
    service.saveAll(reports);

    LOG.debug("Exit.");
  }
}
