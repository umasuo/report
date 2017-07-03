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
@Table(name = "user_report")
public class UserReport {

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
   * 统计的那个小时的其实时间戳.
   */
  private Long startTime;

  /**
   * 这个小时内新增的用户数量.
   */
  private Integer increaseNumber;

}
