package com.umasuo.report.application.service;

import com.umasuo.report.application.dto.DeviceReportDraft;
import com.umasuo.report.application.dto.DeviceReportView;
import com.umasuo.report.application.dto.mapper.DeviceReportMapper;
import com.umasuo.report.domain.model.DeviceReport;
import com.umasuo.report.domain.service.DeviceReportService;
import com.umasuo.report.infrastructure.enums.ReportType;
import com.umasuo.report.infrastructure.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

  private static long SECOND_OF_DAY = 86400;

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
   * Gets report by type.
   *
   * @param developerId the developer id
   * @param reportType  the report type
   * @return the report by type
   */
  public List<DeviceReportView> getReportByType(String developerId, String reportType, String
      timeZone) {
    logger.debug("Enter. reportType: {}.", reportType);

    List<DeviceReportView> result = new ArrayList<>();
    if (reportType.equals(ReportType.DAILY.getType())) {
      result = getDailyReport(developerId, timeZone);
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
  private List<DeviceReportView> getDailyReport(String developerId, String timeZone) {
    logger.debug("Enter. developerId: {}.");

    long endTime = DateUtils.getStartTime(timeZone);
    long startTime = endTime - SECOND_OF_DAY * 30;

    List<DeviceReport> hourlyReport = service.getReportByDate(developerId, startTime, endTime);
    //如此转换有一个前提，就是每次统计都需要正确完成，如果完成不正确，那么统计出的数据可能会跨天
    List<DeviceReportView> dailyReport = DeviceReportMapper.hourlyToDaily(hourlyReport);

    return dailyReport;
  }

  /**
   * 定时任务执行时，用来处理定时任务中拉取到的数据.
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
