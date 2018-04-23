package com.atos.coderank.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.atos.coderank.entities.GroupEntity;
import com.atos.coderank.entities.ProjectEntity;

@Repository("projectRepository")
public interface ProjectRepository extends JpaRepository<ProjectEntity, String>{

	ProjectEntity findByProjectId(String projectId);

	ProjectEntity findByGroup(GroupEntity ge);

	@Query(value = "Select * From group_projects where :test", nativeQuery = true)
	List<ProjectEntity> sqltest(@Param("test") String test);
}
