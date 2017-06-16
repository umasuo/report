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
  private static final Logger LOG = LoggerFactory.getLogger(UserReportService.class);

  /**
   * The Repository.
   */
  @Autowired
  private transient UserReportRepository repository;

  /**
   * Gets report by date.
   *
   * @param developerId the developer id
   * @param startDate the start date
   * @param endDate the end date
   * @return the report by date
   */
  public List<UserReport> getReportByDate(String developerId, String startDate, String endDate) {
    LOG.debug("Enter. developerId: {}, startDate: {}, endDate: {}.",
        developerId, startDate, endDate);

    List<UserReport> reports = repository.getReportByDate(developerId, startDate, endDate);

    return reports;
  }

  /**
   * Save all.
   *
   * @param reports the reports
   */
  public void saveAll(List<UserReport> reports) {
    LOG.debug("Enter. reports size: {}.", reports.size());

    repository.save(reports);

    LOG.debug("Exit.");
  }
}
