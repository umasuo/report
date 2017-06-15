package com.umasuo.report.infrastructure.repository;

import com.umasuo.report.domain.model.UserReport;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Davis on 17/6/15.
 */
public interface UserRepositoryReport extends JpaRepository<UserReport, String> {

}
