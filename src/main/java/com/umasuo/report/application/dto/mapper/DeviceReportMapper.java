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
    //TODO finish this data

    return model;
  }

  /**
   * To entity list.
   *
   * @param reportDrafts the report drafts
   * @param startTime    the yesterday date
   * @return the list
   */
  public static List<DeviceReport> toEntity(List<DeviceReportDraft> reportDrafts, Long startTime) {
    List<DeviceReport> entities = Lists.newArrayList();

    reportDrafts.stream().forEach(
        reportDraft -> {
          if (reportDraft.getStartTime().equals(startTime)) {
            entities.add(toEntity(reportDraft, startTime));
          }
        }
    );

    return entities;
  }

  /**
   * To entity device report.
   *
   * @param draft     the draft
   * @param startTime the yesterday date
   * @return the device report
   */
  public static DeviceReport toEntity(DeviceReportDraft draft, Long startTime) {
    DeviceReport entity = new DeviceReport();

    entity.setDeveloperId(draft.getDeveloperId());
    entity.setDeviceDefinitionId(draft.getDeviceDefinitionId());
    entity.setStartTime(startTime);
    entity.setIncreaseNumber(draft.getIncreaseNumber());
    entity.setActiveNumber(draft.getActiveNumber());
    entity.setTotalNumber(draft.getTotalNumber());

    return entity;
  }
}
