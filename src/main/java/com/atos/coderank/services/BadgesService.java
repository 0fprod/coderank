package com.atos.coderank.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atos.coderank.components.QualificationCalculator;
import com.atos.coderank.entities.BadgesEntity;
import com.atos.coderank.entities.ProjectEntity;
import com.atos.coderank.repositories.BadgesRepository;

@Service("badgesService")
public class BadgesService {

	private static final Log LOG = LogFactory.getLog(BadgesService.class);
	private static final String GOLD = "gold";
	private static final String SILVER = "silver";
	private static final String BRONZE = "bronze";
	private static final String DOMAIN = "domain";
	
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
	 * Updates the badges and qualification of a project
	 * @param project
	 */
	@SuppressWarnings("unchecked")
	public void updateBadges(ProjectEntity project) {
		Map<String, Object> map = calcBadgesAndQualification(project);
		List<BadgesEntity> list = (List<BadgesEntity>) map.get("badges");
		
		for (BadgesEntity badge : list) {
			badge.addProject(project);
			this.saveOrUpdate(badge);
		}
		
		
		
	}
	
	/**
	 * Calculates the qualification and the badges of the project according to its
	 * projectMetrics values
	 * 
	 * @param project
	 * @return
	 */
	private Map<String, Object> calcBadgesAndQualification(ProjectEntity project) {
		List<BadgesEntity> allBadges = this.br.findAllByBadgeNameStartingWithOrderedByBadgeIdAsc("project_%");
		ArrayList<BadgesEntity> badges = new ArrayList<>();
		int last = project.getMetrics().size() - 1;
		QualificationCalculator qc = new QualificationCalculator();
		qc.calcMetrics(project.getMetrics().get(last));

		int numberOfMetrics = 8; // from 0 to 7

		for (int i = 0; i < numberOfMetrics; i++) {
			if (qc.getAttribute(i) != null) {
				try {
					int index;
					switch (qc.getAttribute(i).get("label").toString()) {
					case "A":
						index = findIndexByDomainAndClass(allBadges, qc.getAttribute(i).get(DOMAIN).toString(), GOLD);
						badges.add(allBadges.get(index));
						break;
					case "B":
						index = findIndexByDomainAndClass(allBadges, qc.getAttribute(i).get(DOMAIN).toString(),	SILVER);
						badges.add(allBadges.get(index));
						break;
					case "C":
						index = findIndexByDomainAndClass(allBadges, qc.getAttribute(i).get(DOMAIN).toString(), BRONZE);
						badges.add(allBadges.get(index));
						break;
					default:
						break;
					}
				} catch (IndexOutOfBoundsException e) {
					LOG.error("Error! Trying to access to position " + i + " -> " + e.getMessage());
				}

			}
		}

		Map<String, Object> map = new HashMap<>();
		map.put("badges", badges);
		map.put("qualification", qc.getQualification());

		return map;
	}

	/**
	 * Returns the index in the list of the given params
	 * 
	 * @param domain
	 * @param badgeClass
	 * @return
	 */
	private int findIndexByDomainAndClass(List<BadgesEntity> list, String domain, String badgeClass) {
		int index = -1;
		int sz = list.size();
		
		for (int i = 0; i < sz; i++) {
			BadgesEntity currentBadge = list.get(i); 
			if(currentBadge.getDomain().equals(domain) && currentBadge.getBadgeClass().equals(badgeClass)) {
				index = i;
				break;
			}
		}
		
		
		return index;
	}
}
