package com.atos.coderank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atos.coderank.components.SonarUtils;
import com.atos.coderank.entities.ProjectEntity;
import com.atos.coderank.entities.ProjectMetricsEntity;
import com.atos.coderank.repositories.ProjectMetricsRepository;

@Service("projectMetricsService")
public class ProjectMetricsService {

	@Autowired
	@Qualifier("sonarUtils")
	private SonarUtils su;

	@Autowired
	@Qualifier("projectService")
	private ProjectService ps;

	@Autowired
	@Qualifier("projectMetricsRepository")
	private ProjectMetricsRepository pmr;

	public ProjectMetricsEntity calcSonarQubeMetrics(ProjectEntity project) {

		ProjectMetricsEntity pme = this.su.scanOneProject(project.getKey());
		project.setProjectId(pme.getProject().getProjectId());
		pme.setProject(project);

		return pme;
	}

	public List<ProjectMetricsEntity> findAllMostRecent() {
		return this.pmr.findMostRecents();
	}

	public ProjectMetricsEntity findMostRecentByProjectId(String projectId) {
		return this.pmr.findMostRecentByProjectId(projectId);
	}

	public void deleteMetricsByProjectId(ProjectEntity project) {
		List<ProjectMetricsEntity> list = project.getMetrics();

		for (ProjectMetricsEntity entity : list)
			this.pmr.deleteById(entity.getMetricsId());

	}
}
