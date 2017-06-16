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

    model.setRegisterNumber(entity.getRegisterNumber());
    model.setOnlineNumber(entity.getOnlineNumber());
    model.setTotalNumber(entity.getTotalNumber());
    model.setDeveloperId(entity.getDeveloperId());
    model.setLocalDate(entity.getLocalDate());

    return model;
  }

  public static List<UserReport> toEntity(List<UserReportView> models, String yesterdayDate) {
    List<UserReport> entities = Lists.newArrayList();

    Consumer<UserReportView> consumer = view -> entities.add(toEntity(view, yesterdayDate));

    models.stream().forEach(consumer);

    return entities;
  }

  private static UserReport toEntity(UserReportView model, String localDate) {
    UserReport entity = new UserReport();

    entity.setRegisterNumber(model.getRegisterNumber());
    entity.setOnlineNumber(model.getOnlineNumber());
    entity.setTotalNumber(model.getTotalNumber());
    entity.setDeveloperId(model.getDeveloperId());
    entity.setLocalDate(localDate);

    return entity;
  }
}
