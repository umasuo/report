package com.umasuo.report.application.service;

import com.umasuo.report.application.dto.UserReportDraft;
import com.umasuo.report.application.dto.UserReportView;
import com.umasuo.report.application.dto.mapper.UserReportMapper;
import com.umasuo.report.domain.model.UserReport;
import com.umasuo.report.domain.service.UserReportService;
import com.umasuo.report.infrastructure.enums.ReportType;
import com.umasuo.report.infrastructure.util.DateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * UserReport application.
 */
@Service
public class UserReportApplication {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(UserReportApplication.class);

  /**
   * Milli second build one day.
   */
  private static final long MILLI_SECOND_OF_DAY = 86400000;

  /**
   * The Service.
   */
  @Autowired
  private transient UserReportService service;

  /**
   * Gets report by type.
   *
   * @param developerId the developer id
   * @param reportType the report type
   * @return the report by type
   */
  public List<UserReportView> getReportByType(String developerId, String reportType, String
      timezone) {
    LOG.debug("Enter. reportType: {}.", reportType);

    List<UserReportView> result;

    if (reportType.equals(ReportType.DAILY.getType())) {
      result = getDailyReport(developerId, timezone);
    } else {
      // TODO: 17/7/3
      result = new ArrayList<>();
    }

    LOG.debug("Exit. device report size: {}.", result.size());
    return result;
  }

  /**
   * Get daily report for user reports data.
   * todo 目前只获取近30天的数据.
   *
   * @param developerId the developer id
   * @return list build DeviceReportView
   */
  private List<UserReportView> getDailyReport(String developerId, String timezone) {
    LOG.debug("Enter. developerId: {}.");

    long endTime = DateUtils.getStartTime(timezone);
    long startTime = endTime - MILLI_SECOND_OF_DAY * 30;

    List<UserReport> hourlyReport = service.getReportByDate(developerId, startTime, endTime);
    //如此转换有一个前提，就是每次统计都需要正确完成，如果完成不正确，那么统计出的数据可能会跨天
    List<UserReportView> dailyReport = UserReportMapper.hourlyToDaily(hourlyReport);

    LOG.debug("Exit. user report: {}.", dailyReport);
    return dailyReport;
  }

  /**
   * Handle yesterday report.
   *
   * @param reportDrafts the report drafts
   */
  public void handleFetchHourlyReport(List<UserReportDraft> reportDrafts, Long startTime) {
    LOG.debug("Enter. report size: {}.", reportDrafts.size());

    List<UserReport> reports = UserReportMapper.toModel(reportDrafts, startTime);

    service.saveAll(reports);

    LOG.debug("Exit.");
  }
}
