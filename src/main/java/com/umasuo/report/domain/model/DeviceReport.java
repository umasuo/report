package com.umasuo.report.domain.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Davis on 17/6/15.
 */
@Data
@Entity
@Table(name = "device_report")
public class DeviceReport {
  /**
   * Uuid.
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  private String id;

  /**
   * The Developer id.
   */
  private String developerId;

  /**
   * The Device definition id.
   */
  private String deviceDefinitionId;

  /**
   * start time of it's hour.
   */
  private Long startTime;

  /**
   * The new increase device number.
   */
  private Integer increaseNumber;

  /**
   * The Online number.
   */
  private Integer activeNumber;

  /**
   * total number of device at this hour.
   */
  private Integer totalNumber;
}