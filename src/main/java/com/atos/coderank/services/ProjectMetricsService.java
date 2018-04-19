package com.atos.coderank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atos.coderank.components.SonarUtils;
import com.atos.coderank.entities.ProjectEntity;
import com.atos.coderank.entities.ProjectMetricsEntity;

@Service("projectMetricsService")
public class ProjectMetricsService {

	@Autowired
	@Qualifier("sonarUtils")
	private SonarUtils su;
	
	@Autowired
	@Qualifier("projectService")
	private ProjectService ps;
	
	public ProjectMetricsEntity calcSonarQubeMetrics(ProjectEntity project) {			
		
		ProjectMetricsEntity pme = this.su.scanOneProject(project.getKey());
		pme.setProject(project);
		
		return pme;
	}
}
