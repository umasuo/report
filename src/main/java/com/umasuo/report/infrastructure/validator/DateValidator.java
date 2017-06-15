package com.umasuo.report.infrastructure.validator;

import static java.time.temporal.ChronoUnit.DAYS;

import com.umasuo.exception.ParametersException;
import com.umasuo.report.infrastructure.config.DateConfig;

import org.hibernate.annotations.SourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Created by Davis on 17/6/15.
 */
public final class DateValidator {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(DateValidator.class);

  /**
   * Instantiates a new Date validator.
   */
  private DateValidator() {
  }

  /**
   * Validate pattern.
   *
   * @param date the date
   */
  public static void validatePattern(String date) {
    try {
      DateConfig.dateTimeFormatter.parse(date);
    } catch (DateTimeParseException ex) {
      LOG.debug("Can not parse date: {}.", date);
      throw new ParametersException("Date should by yyyyMMdd pattern");
    }
  }

  public static void validatePeriod(String startString, String endString) {

    LocalDate startLocalDate = LocalDate.parse(startString, DateConfig.dateTimeFormatter);
    ZonedDateTime startTime = startLocalDate.atStartOfDay(DateConfig.zoneId);


    LocalDate endLocalDate = LocalDate.parse(endString, DateConfig.dateTimeFormatter);
    ZonedDateTime endTime = endLocalDate.atStartOfDay(DateConfig.zoneId);

    long days = DAYS.between(startTime, endTime);
    if (days > 365L) {
      LOG.debug("Can not get report more than 1 year. startDate: {}, endDate: {}.",
          startString, endString);
      throw new ParametersException("Date should between 1 year");
    }
  }
}
