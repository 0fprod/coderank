package com.atos.coderank.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atos.coderank.components.ProjectCalculator;
import com.atos.coderank.components.UtilsComponent;
import com.atos.coderank.entities.GroupEntity;
import com.atos.coderank.entities.ProjectEntity;
import com.atos.coderank.entities.ProjectMetricsEntity;
import com.atos.coderank.repositories.ProjectRepository;

@Service("projectService")
public class ProjectService {

	private static final Log LOG = LogFactory.getLog(ProjectService.class);
	
	@Autowired
	@Qualifier("projectCalculator")
	private ProjectCalculator pc;

	@Autowired
	@Qualifier("projectRepository")
	private ProjectRepository pr;

	@Autowired
	@Qualifier("projectMetricsService")
	private ProjectMetricsService pms;

	@Autowired
	@Qualifier("badgesService")
	private BadgesService bs;

	@Autowired
	@Qualifier("rankingService")
	private RankingService rs;
	
	@Autowired
	@Qualifier("groupService")
	private GroupService gs;

	@Autowired
	@Qualifier("utilsComponent")
	private UtilsComponent uc;

	public ProjectEntity saveOrUpdate(ProjectEntity project) {
		ProjectEntity entity = this.pr.findByProjectId(project.getProjectId());
		ProjectEntity saved = null;

		if (null == entity) { // new project
			LOG.info("Creating new project");
			// NotNull
			entity = new ProjectEntity();
			entity.setName(project.getName());
			entity.setKey(project.getKey());
			entity.setUrl(project.getUrl());

			// Defaults
			entity.setLogo(this.uc.loadImage("./src/main/resources/static/images/defaults/project_default.png"));
			entity.setCreatedDate(new Date());
			entity.setLocked(false);

			// Set Metrics
			ProjectMetricsEntity pme = this.pms.calcSonarQubeMetrics(project);
			entity.setProjectId(pme.getProject().getProjectId()); // The ID given from sonarqube
			entity.setMetrics((entity.getMetrics() == null) ? new ArrayList<>() : entity.getMetrics());
			entity.getMetrics().add(pme);

			this.pc.setProject(entity); //Calculate metrics and give value to ranking and badges
			
			saved = this.pr.saveAndFlush(entity); 
			saved.setRanking(this.pc.calcRanking());
			saved.setBadges(this.pc.calcBadges());

			// Set ranking and badges
			this.rs.updateRanking(saved);
			this.bs.updateBadges(saved);

		} else {
			LOG.info("Updating project");
			entity.setLogo(project.getLogo() == null ? entity.getLogo() : project.getLogo());
			entity.setLocked(project.isLocked() == null ? entity.isLocked() : project.isLocked());
			entity.setLockedDate(project.getLockedDate() == null ? entity.getLockedDate() : project.getLockedDate());
			entity.setUrl(project.getUrl() == null ? entity.getUrl() : project.getUrl());
			entity.setName(project.getName() == null ? entity.getName() : project.getName());
			saved = this.pr.saveAndFlush(entity);
		}

		// Save group in case it has been sent
		if (project.getSerializedGroup() != null) {
			GroupEntity group = this.gs.findByGroupId(Long.parseLong(project.getSerializedGroup().get("groupId")));
			saved.setGroup(group);
			group.setProject(project);
			this.gs.saveOrUpdate(group);
		}

		return saved;

	}

	public List<ProjectEntity> findAll() {
		return this.pr.findAll();
	}

	public ProjectEntity findById(String projectId) {
		return this.pr.findByProjectId(projectId);
	}

	public void delete(ProjectEntity projectToDelete) {
		this.pr.delete(projectToDelete);
	}

	public List<ProjectEntity> findAllByGroupId(List<Long> groupsIdsL) {
		List<ProjectEntity> list = new ArrayList<>();

		for (Long id : groupsIdsL) {
			GroupEntity ge = new GroupEntity();
			ge.setGroupId(id);
			ProjectEntity project = this.pr.findByGroup(ge);
			if (project != null)
				list.add(project);
		}

		return list;
	}
}
