package com.atos.coderank.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atos.coderank.entities.ProjectEntity;
import com.atos.coderank.repositories.ProjectRepository;

@Service("projectService")
public class ProjectService {

	@Autowired
	@Qualifier("projectRepository")
	private ProjectRepository pr;

	public ProjectEntity saveOrUpdate(ProjectEntity project) {
		ProjectEntity entity = this.pr.findByProjectId(project.getProjectId());

		if (null == entity) { // Proyecto nuevo
			// NotNull
			entity = new ProjectEntity();
			entity.setProjectId(project.getProjectId());
			entity.setName(project.getName());
			entity.setKey(project.getKey());
			entity.setUrl(project.getUrl());
			if(project.getGroup() != null) {				
				entity.setGroup(project.getGroup());
			}
			// Defaults
			// TODO default logo entity.setLogo();
			entity.setCreatedDate(new Date());
			entity.setLocked(false);
		} else {
			entity.setLogo(project.getLogo() == null ? entity.getLogo() : project.getLogo());
			entity.setLocked(project.isLocked() == null ? entity.isLocked() : project.isLocked());
			entity.setLockedDate(project.getLockedDate() == null ? entity.getLockedDate() : project.getLockedDate());
			entity.setUrl(project.getUrl() == null ? entity.getUrl() : project.getUrl());
			entity.setName(project.getName() == null ? entity.getName() : project.getName());
		}

		return this.pr.saveAndFlush(entity);
	}

	public List<ProjectEntity> findAll() {
		return this.pr.findAll();
	}

	public ProjectEntity findById(String project_id) {
		return this.pr.findByProjectId(project_id);
	}
}
