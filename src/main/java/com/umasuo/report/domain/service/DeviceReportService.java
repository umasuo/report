package com.umasuo.report.domain.service;

import com.umasuo.report.domain.model.DeviceReport;
import com.umasuo.report.infrastructure.repository.DeviceReportRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Davis on 17/6/15.
 */
@Service
public class DeviceReportService {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(DeviceReportService.class);

  /**
   * The Device report repository.
   */
  @Autowired
  private DeviceReportRepository repository;

  /**
   * Gets report by date.
   *
   * @param developerId the developer id
   * @param startDate the start date
   * @param endDate the end date
   * @return the report by date
   */
  public List<DeviceReport> getReportByDate(String developerId, String startDate, String endDate) {
    LOG.debug("Enter. developerId: {}, startDate: {}, endDate: {}.",
        developerId, startDate, endDate);

    List<DeviceReport> reports = repository.getReportByDate(developerId, startDate, endDate);

    return reports;
  }


  /**
   * Save all.
   *
   * @param reports the reports
   */
  public void saveAll(List<DeviceReport> reports) {
    LOG.debug("Enter. reports size: {}.", reports.size());

    repository.save(reports);

    LOG.debug("Exit.");
  }
}
