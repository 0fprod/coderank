package com.atos.coderank.components;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.atos.coderank.entities.ProjectEntity;
import com.atos.coderank.entities.ProjectMetricsEntity;
import com.atos.coderank.services.BadgesService;
import com.atos.coderank.services.ProjectMetricsService;
import com.atos.coderank.services.ProjectService;
import com.atos.coderank.services.RankingService;

@Component("scheduledTasks")
public class ScheduledTasks {

	private static final Log LOG = LogFactory.getLog(ScheduledTasks.class);
	
	@Autowired
	@Qualifier("projectService")
	private ProjectService ps;
	
	@Autowired
	@Qualifier("projectMetricsService")
	private ProjectMetricsService pms;
	
	@Autowired
	@Qualifier("projectCalculator")
	private ProjectCalculator pc;
	
	@Autowired
	@Qualifier("rankingService")
	private RankingService rs;
	
	@Autowired
	@Qualifier("badgesService")
	private BadgesService bs;
	
	@Autowired
	@Qualifier("sonarUtils")
	private SonarUtils su;
	
	//TODO check if hasNewRevision
	public void syncAllProjectsMetrics() {
		LOG.info("Syncing all project metrics...");
		List<ProjectEntity> projects = this.ps.findAll();
		
		for (ProjectEntity project : projects) {
			// Calc new metrics
			ProjectMetricsEntity pme = this.su.scanOneProject(project.getKey());
			project.getMetrics().add(pme);
			ProjectEntity saved = this.ps.saveAndFlush(project);
			
			// Calc new badges & new ranking
			this.pc.setProject(project);
			saved.setRanking(this.pc.calcRanking());
			saved.setBadges(this.pc.calcBadges());
			
			this.bs.updateBadges(saved);
			this.rs.updateRanking(saved);
			
		}
	}
	
	
}
