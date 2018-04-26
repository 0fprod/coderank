package com.atos.coderank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atos.coderank.entities.ProjectReportsEntity;

@Repository("projectReportsRepository")
public interface ProjectReportsRepository extends JpaRepository<ProjectReportsEntity, Long>{

}
