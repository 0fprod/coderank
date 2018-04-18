package com.atos.coderank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atos.coderank.entities.ProjectMetricsEntity;

@Repository("projectMetricsRepository")
public interface ProjectMetricsRepository extends JpaRepository<ProjectMetricsEntity, Long> {

}
