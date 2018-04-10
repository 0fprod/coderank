package com.atos.coderank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atos.coderank.entities.ProjectEntity;

@Repository("projectRepository")
public interface ProjectRepository extends JpaRepository<ProjectEntity, String>{

	ProjectEntity findByProjectId(String projectId);

}
