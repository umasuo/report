package com.umasuo.report.application.rest;

import static com.umasuo.report.infrastructure.Router.USER_REPORT_ROOT;

import com.umasuo.report.application.dto.UserReportView;
import com.umasuo.report.application.service.UserReportApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Davis on 17/6/16.
 */
@RestController
public class UserReportController {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(UserReportController.class);

  /**
   * The Application.
   */
  @Autowired
  private UserReportApplication application;

  /**
   * Gets report by period.
   *
   * @param developerId the developer id
   * @param startDate the start date
   * @param endDate the end date
   * @return the report by period
   */
  @GetMapping(value = USER_REPORT_ROOT, params = {"startDate", "endDate"})
  public List<UserReportView> getReportByPeriod(@RequestHeader("developerId") String developerId,
      @RequestParam("startDate") String startDate,
      @RequestParam("endDate") String endDate) {
    LOG.info("Enter. startDate: {}, endDate: {}.", startDate, endDate);

    List<UserReportView> result = application.getReportByPeriod(developerId, startDate, endDate);

    LOG.info("Exit. user report size: {}.", result.size());

    return result;
  }

  /**
   * Gets report by type.
   *
   * @param type the report type
   * @return the report by type
   */
  @GetMapping(value = USER_REPORT_ROOT, params = {"type"})
  public List<UserReportView> getReportByType(@RequestHeader("developerId") String developerId,
      @RequestParam("type") String type) {
    LOG.info("Enter. report type: {}.", type);

    List<UserReportView> result = application.getReportByType(developerId, type);

    LOG.info("Exit. user report: {}.", result);

    return result;
  }
}
