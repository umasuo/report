package com.umasuo.report.application.service;

import com.google.common.collect.Lists;
import com.umasuo.report.application.dto.DeviceReportDraft;
import com.umasuo.report.application.dto.DeviceReportView;
import com.umasuo.report.application.dto.UserReportDraft;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Davis on 17/6/16.
 */
@Service
public class RestClient {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(RestClient.class);

  @Value("${device.center.service.url:http://device-center/}")
  private String deviceCenterUrl;

  @Value("${user.service.url:http://users/}")
  private String userUrl;

  /**
   * Rest template.
   */
  private RestTemplate restTemplate = new RestTemplate();

  /**
   * Gets yesterday report.
   *
   * @param startTime the start time
   * @param endTime   the end time
   * @return the yesterday report
   */
  public List<DeviceReportDraft> getDeviceReport(long startTime, long endTime) {
    LOG.info("Enter. startTime: {}, endTime: {}.", startTime, endTime);

    String url = UriComponentsBuilder.fromHttpUrl(deviceCenterUrl + "/v1/devices/reports/")
        .queryParam("startTime", startTime)
        .queryParam("endTime", endTime).build().encode().toUriString();

    DeviceReportDraft[] reportDrafts = restTemplate.getForObject(url, DeviceReportDraft[].class);

    List<DeviceReportDraft> result = Lists.newArrayList(reportDrafts);

    return result;
  }

  /**
   * Gets real time device report.
   *
   * @param startTime   the start time
   * @param developerId the developer id
   * @return the real time device report
   */
  public List<DeviceReportView> getRealTimeDeviceReport(long startTime, String developerId) {
    LOG.info("Enter. startTime: {}, developerId: {}.", startTime, developerId);

    HttpHeaders headers = new HttpHeaders();
    headers.set("developerId", developerId);

    HttpEntity entity = new HttpEntity(headers);

    String url = UriComponentsBuilder.fromHttpUrl(deviceCenterUrl + "/v1/devices/reports/")
        .queryParam("startTime", startTime).build().encode().toUriString();

    ResponseEntity<DeviceReportView[]> response =
        restTemplate.exchange(url, HttpMethod.GET, entity, DeviceReportView[].class);

    List<DeviceReportView> result = Lists.newArrayList(response.getBody());

    LOG.info("Exit. report size: {}.", result);
    return result;
  }

  /**
   * Gets real time device report.
   *
   * @param startTime   the start time
   * @param developerId the developer id
   * @return the real time device report
   */
  public UserReportDraft getRealTimeUserReport(long startTime, String developerId) {
    LOG.info("Enter. startTime: {}, developerId: {}.", startTime, developerId);

    HttpHeaders headers = new HttpHeaders();
    headers.set("developerId", developerId);

    HttpEntity entity = new HttpEntity(headers);

    String url = UriComponentsBuilder.fromHttpUrl(userUrl + "/v1/users/reports/")
        .queryParam("startTime", startTime).build().encode().toUriString();

    ResponseEntity<UserReportDraft> response =
        restTemplate.exchange(url, HttpMethod.GET, entity, UserReportDraft.class);

    UserReportDraft result = response.getBody();

    LOG.info("Exit. report size: {}.", result);
    return result;
  }


  /**
   * Gets user report.
   *
   * @param startTime the start time
   * @param endTime   the end time
   * @return the user report
   */
  public List<UserReportDraft> getUserReport(long startTime, long endTime) {
    LOG.info("Enter. startTime: {}, endTime: {}.", startTime, endTime);

    String url = UriComponentsBuilder.fromHttpUrl(userUrl + "/v1/users/reports/")
        .queryParam("startTime", startTime)
        .queryParam("endTime", endTime).build().encode().toUriString();

    UserReportDraft[] reportDrafts = restTemplate.getForObject(url, UserReportDraft[].class);

    List<UserReportDraft> result = Lists.newArrayList(reportDrafts);

    return result;
  }
}
