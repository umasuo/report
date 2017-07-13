package com.umasuo.report.application.dto.mapper;

import com.google.common.collect.Lists;
import com.umasuo.report.application.dto.UserReportDraft;
import com.umasuo.report.application.dto.UserReportView;
import com.umasuo.report.domain.model.UserReport;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Davis on 17/6/16.
 */
public final class UserReportMapper {

  /**
   * Instantiates a new User report mapper.
   */
  private UserReportMapper() {
  }


  public static List<UserReportDraft> toModel(List<UserReport> entities) {
    List<UserReportDraft> models = Lists.newArrayList();

    Consumer<UserReport> consumer = report -> models.add(toModel(report));

    entities.stream().forEach(consumer);

    return models;
  }

  private static UserReportDraft toModel(UserReport entity) {
    UserReportDraft model = new UserReportDraft();

    model.setDeveloperId(entity.getDeveloperId());
    model.setStartTime(entity.getStartTime());
    model.setIncreaseNumber(entity.getIncreaseNumber());
    return model;
  }

  /**
   * convert report view to entity.
   * report view comes from other services.
   *
   * @param views     UserReportDraft
   * @param startTime Long
   * @return
   */
  public static List<UserReport> toEntity(List<UserReportDraft> views, Long startTime) {

    List<UserReport> entities = Lists.newArrayList();

    views.stream().forEach(
        view -> {
          entities.add(toEntity(view, startTime));
        }
    );

    return entities;
  }

  private static UserReport toEntity(UserReportDraft view, Long startTime) {
    UserReport entity = new UserReport();
    entity.setDeveloperId(view.getDeveloperId());
    entity.setStartTime(startTime);
    entity.setIncreaseNumber(view.getIncreaseNumber());
    entity.setTotalNumber(view.getTotalNumber());
    return entity;
  }


  /**
   * 将以小时为单位统计的数据转换为以天为单位统计的数据,这一步可以在数据库中完成.
   *
   * @param hourlyReport
   * @return
   */
  public static List<UserReportView> hourlyToDaily(List<UserReport> hourlyReport) {
    List<UserReportView> result = new ArrayList<>();
    int count = 0;

    while (count * 24 < hourlyReport.size()) {
      int startCount = count * 24;
      int endCount = (count + 1) * 24 - 1;
      endCount = endCount > hourlyReport.size() ? hourlyReport.size() - 1 : endCount;

      hourlyToDaily(result, hourlyReport.subList(startCount, endCount));

      count++;
    }

    return result;
  }

  /**
   * 将以小时为单位的统计数据，merge成一条数据。
   * 最多24小时的24条数据.
   *
   * @param result
   * @param hourlyReport
   */
  private static void hourlyToDaily(List<UserReportView> result, List<UserReport> hourlyReport) {

    UserReportView view = new UserReportView();
    hourlyReport.stream().forEach(
        userReport -> {
          view.setIncreaseNumber(view.getIncreaseNumber() + userReport.getIncreaseNumber());
          view.setTotalNumber(userReport.getTotalNumber());
          view.setActiveNumber(userReport.getActiveNumber());
        }
    );
    view.setDate(hourlyReport.get(0).getStartTime() / 1000);//换算成秒

    result.add(view);
  }


}
