package com.umasuo.report.application.dto.mapper;

import com.google.common.collect.Lists;
import com.umasuo.report.application.dto.UserReportView;
import com.umasuo.report.domain.model.UserReport;

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


  public static List<UserReportView> toModel(List<UserReport> entities) {
    List<UserReportView> models = Lists.newArrayList();

    Consumer<UserReport> consumer = report -> models.add(toModel(report));

    entities.stream().forEach(consumer);

    return models;
  }

  private static UserReportView toModel(UserReport entity) {
    UserReportView model = new UserReportView();

    model.setDeveloperId(entity.getDeveloperId());
    model.setStartTime(entity.getStartTime());
    model.setIncreaseNumber(entity.getIncreaseNumber());
    return model;
  }

  /**
   * convert report view to entity.
   * report view comes from other services.
   *
   * @param views     UserReportView
   * @param startTime Long
   * @return
   */
  public static List<UserReport> toEntity(List<UserReportView> views, Long startTime) {

    List<UserReport> entities = Lists.newArrayList();

    views.stream().forEach(
        view -> {
          if (view.getStartTime().equals(startTime)) {
            entities.add(toEntity(view, startTime));
          }
        }
    );

    return entities;
  }

  private static UserReport toEntity(UserReportView model, Long startTime) {
    UserReport entity = new UserReport();
    entity.setDeveloperId(model.getDeveloperId());
    entity.setIncreaseNumber(model.getIncreaseNumber());
    entity.setStartTime(startTime);
    return entity;
  }
}
