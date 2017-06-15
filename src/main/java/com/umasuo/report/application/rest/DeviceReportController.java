package com.umasuo.report.application.rest;

import com.umasuo.report.application.dto.DeviceReportView;
import com.umasuo.report.application.service.DeviceReportApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Davis on 17/6/15.
 */
@RestController
public class DeviceReportController {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(DeviceReportController.class);

  /**
   * The Application.
   */
  @Autowired
  private DeviceReportApplication application;

  /**
   * Gets report by period.
   *
   * @param developerId the developer id
   * @param startDate the start date
   * @param endDate the end date
   * @return the report by period
   */
  @GetMapping("/deviceReports/period")
  public List<DeviceReportView> getReportByPeriod(@RequestHeader("developerId") String developerId,
      @RequestParam("startDate") String startDate,
      @RequestParam("endDate") String endDate) {
    LOG.info("Enter. startDate: {}, endDate: {}.", startDate, endDate);

    List<DeviceReportView> result = application.getReportByPeriod(developerId, startDate, endDate);

    LOG.info("Exit. device report size: {}.", result);

    return result;
  }


  /**
   * Gets report by type.
   *
   * @param type the report type
   * @return the report by type
   */
  @GetMapping("/deviceReports/type")
  public List<DeviceReportView> getReportByType(@RequestHeader("developerId") String developerId,
      @RequestParam("type") String type) {
    LOG.info("Enter. report type: {}.", type);

    List<DeviceReportView> result = application.getReportByType(developerId, type);

    LOG.info("Exit. device report size: {}.", result);

    return result;
  }
}
