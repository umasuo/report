package com.umasuo.report.domain.service;

import com.umasuo.report.domain.model.UserReport;
import com.umasuo.report.infrastructure.repository.UserReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Davis on 17/6/16.
 */
@Service
public class UserReportService {

  /**
   * Logger.
   */
  private static final Logger logger = LoggerFactory.getLogger(UserReportService.class);

  /**
   * The Repository.
   */
  @Autowired
  private transient UserReportRepository repository;

  /**
   * Gets report by date.
   *
   * @param developerId the developer id
   * @param startDate   the start date
   * @param endDate     the end date
   * @return the report by date
   */
  public List<UserReport> getReportByDate(String developerId, Long startDate, Long endDate) {
    logger.debug("Enter. developerId: {}, startDate: {}, endDate: {}.",
        developerId, startDate, endDate);

    List<UserReport> hourlyReport = repository.getReportByDate(developerId, startDate, endDate);

    return hourlyReport;
  }

  /**
   * Save all.
   *
   * @param reports the reports
   */
  public void saveAll(List<UserReport> reports) {
    logger.debug("Enter. reports size: {}.", reports.size());

    repository.save(reports);

    logger.debug("Exit.");
  }
}
