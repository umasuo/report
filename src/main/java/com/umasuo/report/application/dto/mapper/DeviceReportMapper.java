package com.umasuo.report.application.dto.mapper;

import com.google.common.collect.Lists;
import com.umasuo.report.application.dto.DeviceReportDraft;
import com.umasuo.report.application.dto.DeviceReportView;
import com.umasuo.report.domain.model.DeviceReport;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Davis on 17/6/15.
 */
public final class DeviceReportMapper {

  /**
   * Instantiates a new Device report mapper.
   */
  private DeviceReportMapper() {
  }

  /**
   * To model list.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<DeviceReportView> toModel(List<DeviceReport> entities) {
    List<DeviceReportView> models = Lists.newArrayList();

    Consumer<DeviceReport> consumer = deviceReport -> models.add(toModel(deviceReport));

    entities.stream().forEach(consumer);

    return models;
  }

  private static DeviceReportView toModel(DeviceReport entity) {
    DeviceReportView model = new DeviceReportView();

    model.setDeviceDefinitionId(entity.getDeviceDefinitionId());
    model.setLocalDate(entity.getLocalDate());
    model.setOnlineNumber(entity.getOnlineNumber());
    model.setRegisterNumber(entity.getRegisterNumber());
    model.setTotalNumber(entity.getTotalNumber());

    return model;
  }

  /**
   * To entity list.
   *
   * @param reportDrafts the report drafts
   * @param yesterdayDate the yesterday date
   * @return the list
   */
  public static List<DeviceReport> toEntity(List<DeviceReportDraft> reportDrafts,
      String yesterdayDate) {
    List<DeviceReport> entities = Lists.newArrayList();

    Consumer<DeviceReportDraft> consumer = deviceReportDraft -> entities
        .add(toEntity(deviceReportDraft, yesterdayDate));

    reportDrafts.stream().forEach(consumer);

    return entities;
  }

  /**
   * To entity device report.
   *
   * @param draft the draft
   * @param yesterdayDate the yesterday date
   * @return the device report
   */
  public static DeviceReport toEntity(DeviceReportDraft draft, String yesterdayDate) {
    DeviceReport entity = new DeviceReport();

    entity.setLocalDate(yesterdayDate);
    entity.setOnlineNumber(draft.getOnlineNumber());
    entity.setRegisterNumber(draft.getRegisterNumber());
    entity.setTotalNumber(draft.getTotalNumber());
    entity.setDeveloperId(draft.getDeveloperId());
    entity.setDeviceDefinitionId(draft.getDeviceDefinitionId());

    return entity;
  }
}
