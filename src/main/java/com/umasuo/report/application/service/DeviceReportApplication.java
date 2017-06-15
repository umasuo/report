package com.umasuo.report.application.service;

import com.google.common.collect.Lists;
import com.umasuo.report.application.dto.DeviceReportView;
import com.umasuo.report.application.dto.mapper.DeviceReportMapper;
import com.umasuo.report.domain.model.DeviceReport;
import com.umasuo.report.domain.service.DeviceReportService;
import com.umasuo.report.infrastructure.enums.ReportType;
import com.umasuo.report.infrastructure.util.DateUtils;
import com.umasuo.report.infrastructure.validator.DateValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Davis on 17/6/15.
 */
@Service
public class DeviceReportApplication {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(DeviceReportApplication.class);

  /**
   * The Service.
   */
  @Autowired
  private DeviceReportService service;

  /**
   * Gets report by period.
   *
   * @param developerId the developer id
   * @param startDate the start date
   * @param endDate the end date
   * @return the report by period
   */
  public List<DeviceReportView> getReportByPeriod(String developerId, String startDate, String endDate) {
    LOG.debug("Enter. developerId: {}, startDate: {}, endDate: {}.",
        developerId, startDate, endDate);

    DateValidator.validatePattern(startDate);
    DateValidator.validatePattern(endDate);
    DateValidator.validatePeriod(startDate, endDate);

    List<DeviceReport> reports = service.getReportByDate(developerId, startDate, endDate);

    List<DeviceReportView> result = DeviceReportMapper.toModel(reports);

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
  public List<DeviceReportView> getReportByType(String developerId, String reportType) {
    LOG.debug("Enter. reportType: {}.", reportType);
    ReportType type = ReportType.build(reportType);

    List<DeviceReportView> result = Lists.newArrayList();
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
  private List<DeviceReportView> getRealTimeReport(String developerId) {
    LOG.debug("Enter. developerId: {}.");

    List<DeviceReportView> result = Lists.newArrayList();

    return result;
  }

  /**
   * Get statistics report.
   *
   * @param developerId the developer id
   * @param type the report date type
   * @return list of DeviceReportView
   */
  private List<DeviceReportView> getStatisticsReport(String developerId, ReportType type) {
    LOG.debug("Enter. reportType: {}.", type);

    String startDate = DateUtils.getStartDate(type);
    String endDate = DateUtils.getEndDate();

    List<DeviceReport> reports = service.getReportByDate(developerId, startDate, endDate);

    List<DeviceReportView> result = DeviceReportMapper.toModel(reports);

    LOG.debug("Exit. device report size: {}.", result.size());

    return result;
  }

}
