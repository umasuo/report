package com.umasuo.report.infrastructure.util;

import com.umasuo.report.application.dto.DeviceReportView;

import java.util.List;

/**
 * Created by Davis on 17/6/16.
 */
public final class ReportUtils {

  /**
   * Instantiates a new Report utils.
   */
  private ReportUtils() {
  }


  /**
   * Calculate device report.
   *
   * @param result the result
   */
  public static void calculateDeviceReport(List<DeviceReportView> result) {

//    DeviceReportView deviceReportView = new DeviceReportView();
//    deviceReportView.setDeviceDefinitionId("totalDevice");
//    deviceReportView.setTotalNumber(0);
//    deviceReportView.setOnlineNumber(0);
//    deviceReportView.setRegisterNumber(0);
//    AtomicReference<DeviceReportView> reference = new AtomicReference<>();
//    reference.set(deviceReportView);
//
//    Consumer<DeviceReportView> consumer = view -> {
//      reference.get().setTotalNumber(reference.get().getTotalNumber() + view.getTotalNumber());
//      reference.get()
//          .setRegisterNumber(reference.get().getRegisterNumber() + view.getRegisterNumber());
//      reference.get().setOnlineNumber(reference.get().getOnlineNumber() + view.getOnlineNumber());
//    };
//
//    result.stream().forEach(consumer);

//    result.add(reference.get());
  }
}
