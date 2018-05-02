package com.atos.coderank.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.atos.coderank.entities.ProjectReportsEntity;

@Repository("projectReportsRepository")
public interface ProjectReportsRepository extends JpaRepository<ProjectReportsEntity, Long> {

	@Query(value = "SELECT * FROM reports WHERE project_project_id in "
			        + " (SELECT p.project_id FROM users u, groups g, groups_users gu, group_projects gp, projects p " 
			        + " WHERE (u.das = :das) AND (u.das = gu.user_das) AND (g.group_id = gu.group_id) AND (g.group_id = gp.group_id) "
			        + " AND (gp.project_id = p.project_id) "
			        + ")", nativeQuery = true)
	abstract List<ProjectReportsEntity> findAllBySQMDas(@Param("das") String das);

	
	abstract ProjectReportsEntity findByReportId(Long id);
}
