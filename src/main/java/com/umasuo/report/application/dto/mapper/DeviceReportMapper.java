package com.umasuo.report.application.dto.mapper;

import com.google.common.collect.Lists;
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
}
