package com.umasuo.report.application.rest;

import static com.umasuo.report.infrastructure.Router.DEVICE_REPORT_ROOT;

import com.umasuo.report.application.dto.DeviceReportView;
import com.umasuo.report.application.service.DeviceReportApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class for DeviceReport.
 */
@CrossOrigin
@RestController
public class DeviceReportController {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(DeviceReportController.class);

  /**
   * The DeviceReportApplication.
   */
  @Autowired
  private transient DeviceReportApplication application;

  /**
   * Gets report by type.
   *
   * @param type the report type
   * @return the report by type
   */
  @GetMapping(value = DEVICE_REPORT_ROOT, params = {"type", "timezone"}, headers = {"developerId"})
  public List<DeviceReportView> getReportByType(@RequestHeader("developerId") String developerId,
      @RequestParam("type") String type,
      @RequestParam("timezone") String timezone) {
    LOG.info("Enter. report type: {}.", type);

    List<DeviceReportView> result = application.getReportByType(developerId, type, timezone);

    LOG.info("Exit. device report size: {}.", result);

    return result;
  }
}
