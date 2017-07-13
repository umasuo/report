package com.umasuo.report.application.rest;

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

import static com.umasuo.report.infrastructure.Router.USER_REPORT_ROOT;

/**
 * Created by Davis on 17/6/16.
 */
@CrossOrigin
@RestController
public class UserReportController {

  /**
   * Logger.
   */
  private static final Logger logger = LoggerFactory.getLogger(UserReportController.class);

  /**
   * The Application.
   */
  @Autowired
  private UserReportApplication application;

  /**
   * 根据 type 和timezone获取报告数据
   *
   * @param developerId 开发者ID
   * @param type        报告类型："daily","weekly", "monthly", "annual"
   * @param timezone    时区值: "GMT+0","GMT+8"...
   * @return
   */
  @GetMapping(value = USER_REPORT_ROOT, params = {"type", "timeZone"}, headers = {"developerId"})
  public List<UserReportView> getReportByType(@RequestHeader("developerId") String developerId,
                                              @RequestParam("type") String type,
                                              @RequestParam("timeZone") String timezone) {
    logger.info("Enter. report type: {}.", type);

    List<UserReportView> result = application.getReportByType(developerId, type, timezone);

    logger.info("Exit. user report: {}.", result);

    return result;
  }
}
