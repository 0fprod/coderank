package com.atos.coderank.services;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atos.coderank.entities.BadgesEntity;
import com.atos.coderank.entities.ProjectEntity;
import com.atos.coderank.repositories.BadgesRepository;

@Service("badgesService")
public class BadgesService {

	private static final Log LOG = LogFactory.getLog(BadgesService.class);

	@Autowired
	@Qualifier("badgesRepository")
	private BadgesRepository br;

	public List<BadgesEntity> findAll() {
		return this.br.findAll();
	}

	public BadgesEntity findById(Long id) {
		return this.br.findByBadgeId(id);
	}

	public BadgesEntity findByName(String name) {
		return this.br.findByName(name);
	}

	public BadgesEntity saveOrUpdate(BadgesEntity badge) {
		return this.br.saveAndFlush(badge);
	}

	/**
	 * Binds a list of badges to the given project
	 * 
	 * @param project
	 */
	public void updateBadges(ProjectEntity project) {
		LOG.info("Upating badges of the project  -" + project.getName() + "-");
		List<BadgesEntity> list = project.getBadges();
		for (BadgesEntity badge : list) {
			badge.addProject(project);
			this.saveOrUpdate(badge);
		}
	}

	public List<BadgesEntity> findAllByBadgeNameStartingWithOrderedByBadgeIdAsc(String string) {
		return this.br.findAllByBadgeNameStartingWithOrderedByBadgeIdAsc(string);
	}
}
