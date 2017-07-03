package com.umasuo.report.application.service;

import com.google.common.collect.Lists;
import com.umasuo.report.application.dto.DeviceReportDraft;
import com.umasuo.report.application.dto.DeviceReportView;
import com.umasuo.report.application.dto.mapper.DeviceReportMapper;
import com.umasuo.report.domain.model.DeviceReport;
import com.umasuo.report.domain.service.DeviceReportService;
import com.umasuo.report.infrastructure.config.DateConfig;
import com.umasuo.report.infrastructure.enums.ReportType;
import com.umasuo.report.infrastructure.util.DateUtils;
import com.umasuo.report.infrastructure.util.ReportUtils;
import com.umasuo.report.infrastructure.validator.DateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Created by Davis on 17/6/15.
 */
@Service
public class DeviceReportApplication {

  /**
   * Logger.
   */
  private static final Logger logger = LoggerFactory.getLogger(DeviceReportApplication.class);

  /**
   * The Service.
   */
  @Autowired
  private DeviceReportService service;

  /**
   * The Rest client.
   */
  @Autowired
  private transient RestClient restClient;

  /**
   * Gets report by period.
   *
   * @param developerId the developer id
   * @param startDate   the start date
   * @param endDate     the end date
   * @return the report by period
   */
  public List<DeviceReportView> getReportByPeriod(String developerId, String startDate,
                                                  String endDate) {
    logger.debug("Enter. developerId: {}, startDate: {}, endDate: {}.",
        developerId, startDate, endDate);

    DateValidator.validatePattern(startDate);
    DateValidator.validatePattern(endDate);
    DateValidator.validatePeriod(startDate, endDate);

    List<DeviceReport> reports = service.getReportByDate(developerId, startDate, endDate);

    List<DeviceReportView> result = DeviceReportMapper.toModel(reports);
    ReportUtils.calculateDeviceReport(result);

    logger.debug("Exit. device report size: {}.", result.size());

    return result;
  }

  /**
   * Gets report by type.
   *
   * @param developerId the developer id
   * @param reportType  the report type
   * @return the report by type
   */
  public List<DeviceReportView> getReportByType(String developerId, String reportType) {
    logger.debug("Enter. reportType: {}.", reportType);

    List<DeviceReportView> result = Lists.newArrayList();
    if (reportType.equals(ReportType.DAILY.getType())) {
      result = getRealTimeReport(developerId);
    } else {
      result = null;
    }

    logger.debug("Exit. device report size: {}.", result.size());
    return result;
  }

  /**
   * Get real time report.
   *
   * @param developerId the developer id
   * @return list of DeviceReportView
   */
  private List<DeviceReportView> getRealTimeReport(String developerId) {
    logger.debug("Enter. developerId: {}.");

    long startDate = ZonedDateTime.now(DateConfig.zoneId)
        .truncatedTo(ChronoUnit.DAYS).toInstant().toEpochMilli();

    List<DeviceReportView> result = restClient.getRealTimeDeviceReport(startDate, developerId);

    ReportUtils.calculateDeviceReport(result);

    return result;
  }

  /**
   * Get statistics report.
   *
   * @param developerId the developer id
   * @param type        the report date type
   * @return list of DeviceReportView
   */
  private List<DeviceReportView> getStatisticsReport(String developerId, ReportType type) {
    logger.debug("Enter. reportType: {}.", type);

    String startDate = DateUtils.getStartDate(type);
    String endDate = DateUtils.getEndDate();

    List<DeviceReport> reports = service.getReportByDate(developerId, startDate, endDate);

    List<DeviceReportView> result = DeviceReportMapper.toModel(reports);
    ReportUtils.calculateDeviceReport(result);

    logger.debug("Exit. device report size: {}.", result.size());

    return result;
  }

  /**
   * Handle yesterday report.
   *
   * @param reportDrafts the report drafts
   */
  public void handleHourlyReport(List<DeviceReportDraft> reportDrafts, Long startTime) {
    logger.debug("Enter. report size: {}.", reportDrafts.size());


    List<DeviceReport> reports = DeviceReportMapper.toEntity(reportDrafts, startTime);
    service.saveAll(reports);

    logger.debug("Exit.");
  }
}
