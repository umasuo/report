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

import static com.umasuo.report.infrastructure.Router.DEVICE_REPORT_ROOT;

/**
 * Created by Davis on 17/6/15.
 */
@RestController
public class DeviceReportController {

  /**
   * Logger.
   */
  private static final Logger logger = LoggerFactory.getLogger(DeviceReportController.class);

  /**
   * The Application.
   */
  @Autowired
  private DeviceReportApplication application;

  /**
   * Gets report by type.
   *
   * @param type the report type
   * @return the report by type
   */
  @GetMapping(value = DEVICE_REPORT_ROOT, params = {"type", "timeZone"}, headers = {"developerId"})
  public List<DeviceReportView> getReportByType(@RequestHeader("developerId") String developerId,
                                                @RequestParam("type") String type,
                                                @RequestParam("timeZone") String timeZone) {
    logger.info("Enter. report type: {}.", type);

    List<DeviceReportView> result = application.getReportByType(developerId, type, timeZone);

    logger.info("Exit. device report size: {}.", result);

    return result;
  }
}
