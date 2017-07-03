package com.umasuo.report.infrastructure.repository;

import com.umasuo.report.domain.model.UserReport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Davis on 17/6/16.
 */
public interface UserReportRepository extends JpaRepository<UserReport, String>{

  /**
   * Gets report by date.
   *
   * @param developerId the developer id
   * @param startDate the start date
   * @param endDate the end date
   * @return the report by date
   */
  @Query("select u from UserReport u where u.developerId = ?1 and u.startTime >= ?2 and u.startTime <= ?3 order by u.startTime")
  List<UserReport> getReportByDate(String developerId, long startDate, long endDate);
}
