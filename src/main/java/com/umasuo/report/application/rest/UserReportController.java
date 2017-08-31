package com.umasuo.report.application.rest;

import static com.umasuo.report.infrastructure.Router.USER_REPORT_ROOT;

import com.umasuo.report.application.dto.UserReportView;
import com.umasuo.report.application.service.UserReportApplication;

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
 * Controller class for UserReport.
 */
@CrossOrigin
@RestController
public class UserReportController {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(UserReportController.class);

  /**
   * The UserReportApplication.
   */
  @Autowired
  private transient UserReportApplication application;

  /**
   * 根据 type 和timezone获取报告数据
   *
   * @param developerId 开发者ID
   * @param type 报告类型："daily","weekly", "monthly", "annual"
   * @param timezone 时区值: "GMT+0","GMT+8"...
   */
  @GetMapping(value = USER_REPORT_ROOT, params = {"type", "timezone"}, headers = {"developerId"})
  public List<UserReportView> getReportByType(@RequestHeader("developerId") String developerId,
      @RequestParam("type") String type,
      @RequestParam("timezone") String timezone) {
    LOG.info("Enter. report type: {}.", type);

    List<UserReportView> result = application.getReportByType(developerId, type, timezone);

    LOG.info("Exit. user report: {}.", result);

    return result;
  }
}
