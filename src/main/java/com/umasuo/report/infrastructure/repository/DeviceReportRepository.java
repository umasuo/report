package com.umasuo.report.infrastructure.repository;

import com.umasuo.report.domain.model.DeviceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Davis on 17/6/15.
 */
public interface DeviceReportRepository extends JpaRepository<DeviceReport, String> {

  /**
   * Gets report by date.
   *
   * @param developerId the developer id
   * @param startDate   the start date
   * @param endDate     the end date
   * @return the report by date
   */
  @Query("select r from DeviceReport r where r.developerId = ?1 and r.startTime >= ?2 and r" +
      ".startTime <= ?3 order by r.startTime")
  List<DeviceReport> getReportByDate(String developerId, long startDate, long endDate);
}
