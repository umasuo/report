package com.umasuo.user.infrastructure.util;

import org.springframework.data.domain.AuditorAware;

import javax.persistence.Converter;

/**
 * Zoned data time auditor.
 */
public class ZonedDateTimeAuditorAware implements AuditorAware<String> {

  /**
   * Return null auditor.
   *
   * @return null
   */
  @Override
  public String getCurrentAuditor() {
    return null;
  }
}