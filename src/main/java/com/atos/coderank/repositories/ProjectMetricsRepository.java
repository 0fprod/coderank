package com.atos.coderank.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.atos.coderank.entities.ProjectMetricsEntity;

@Repository("projectMetricsRepository")
public interface ProjectMetricsRepository extends JpaRepository<ProjectMetricsEntity, Long> {

	@Query(value = "SELECT * FROM metrics q1 WHERE version_date = (SELECT MAX(q2.version_date) FROM metrics q2 WHERE q2.project_id = q1.project_id)", nativeQuery = true)
	abstract List<ProjectMetricsEntity> findMostRecents();

	@Query(value = "SELECT * FROM metrics q1 WHERE version_date = (SELECT MAX(q2.version_date) FROM metrics q2 WHERE q2.project_id = :pid)", nativeQuery = true)
	abstract ProjectMetricsEntity findMostRecentByProjectId(@Param("pid") String projectId);

	abstract List<ProjectMetricsEntity> findAllByProjectProjectId(String projectId);
	
	abstract ProjectMetricsEntity findByMetricsId(Long id);
	
	abstract List<ProjectMetricsEntity> findTop1ByProjectProjectIdOrderByVersionDateDesc(String projectId);
	
	abstract List<ProjectMetricsEntity> findTop10ByProjectProjectIdOrderByVersionDateDesc(String projectId);
}
