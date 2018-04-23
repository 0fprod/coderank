package com.atos.coderank.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.atos.coderank.entities.BadgesEntity;
import com.atos.coderank.entities.ProjectEntity;
import com.atos.coderank.entities.ProjectMetricsEntity;
import com.atos.coderank.entities.RankingEntity;
import com.atos.coderank.services.BadgesService;

@Component("projectCalculator")
public class ProjectCalculator {

	private static final Log LOG = LogFactory.getLog(ProjectCalculator.class);

	@Autowired
	@Qualifier("badgesService")
	private BadgesService bs;

	private static final String GOLD = "gold";
	private static final String SILVER = "silver";
	private static final String BRONZE = "bronze";

	private static final String DOMAIN = "domain";
	private static final String VALUE = "value";
	private static final String LABEL = "label";

	private double qualification;
	private int size;
	private Map<String, Object> security;
	private Map<String, Object> testing;
	private Map<String, Object> complexity;
	private Map<String, Object> issues;
	private Map<String, Object> maintainability;
	private Map<String, Object> documentation;
	private Map<String, Object> duplication;
	private Map<String, Object> reliability;

	/**
	 * Calculates the badges and qualification of the given project (must have at
	 * least 1 projectMetrics)
	 * 
	 * @param project
	 */
	public void setProject(ProjectEntity project) {
		int last = project.getMetrics().size() - 1;
		calcMetrics(project.getMetrics().get(last));
	}

	/**
	 * Returns the list of badges that this project deservces
	 * 
	 * @return
	 */
	public List<BadgesEntity> calcBadges() {
		List<BadgesEntity> allBadges = this.bs.findAllByBadgeNameStartingWithOrderedByBadgeIdAsc("project_%");
		List<BadgesEntity> badges = new ArrayList<>();

		int numberOfMetrics = 8; // from 0 to 7

		for (int i = 0; i < numberOfMetrics; i++) {

			try {
				int index;
				switch (getAttribute(i).get(LABEL).toString()) {
				case "A":
					index = findIndexByDomainAndClass(allBadges, getAttribute(i).get(DOMAIN).toString(), GOLD);
					badges.add(allBadges.get(index));
					break;
				case "B":
					index = findIndexByDomainAndClass(allBadges, getAttribute(i).get(DOMAIN).toString(), SILVER);
					badges.add(allBadges.get(index));
					break;
				case "C":
					index = findIndexByDomainAndClass(allBadges, getAttribute(i).get(DOMAIN).toString(), BRONZE);
					badges.add(allBadges.get(index));
					break;
				default:
					break;
				}
			} catch (IndexOutOfBoundsException e) {
				LOG.error("Error! Trying to access to position " + i + " -> " + e.getMessage());
			} catch (Exception e ) {
				LOG.error("Error! --> " + e.getMessage());
			}

		}

		return badges;
	}

	/**
	 * Calculate the qualification
	 * 
	 * @param project
	 * @return
	 */
	public RankingEntity calcRanking() {
		return new RankingEntity(getQualification());
	}

	/**
	 * Set the values to the domains by the given metrics
	 * 
	 * @param pme
	 */
	private void calcMetrics(ProjectMetricsEntity pme) {
		setSize(pme.getSizLines());
		setSecurity(pme.getSecVulnerabilities());
		// TODO setTesting(pme.getWhat?)
		setComplexity(pme.getComComplexity());
		setIssues(pme.getIssViolations());
		setMaintainability(pme.getMainSqaleRating());
		setDocumentation(pme.getDocCommentLinesDensity());
		setDuplication(pme.getDupDuplicatedLinesDensity());
		setReliability(pme.getRelReliabilityRating());
		setQualification();
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
			if (currentBadge.getDomain().equals(domain) && currentBadge.getBadgeClass().equals(badgeClass)) {
				index = i;
				break;
			}
		}

		return index;
	}

	/**
	 * Returns the attribute of then given index
	 * 
	 * @param i
	 * @return
	 */
	private Map<String, Object> getAttribute(int i) {
		switch (i) {
		case 0:
			return getSecurity();
		case 1:
			return getTesting();
		case 2:
			return getComplexity();
		case 3:
			return getIssues();
		case 4:
			return getMaintainability();
		case 5:
			return getDocumentation();
		case 6:
			return getDuplication();
		case 7:
			return getReliability();
		default:
			return new HashMap<>();
		}
	}

	/**
	 * Less is better
	 * 
	 * @param vulnerabilities
	 */
	public void setSecurity(double vulnerabilities) {
		Map<String, Object> m = new HashMap<>();
		if (vulnerabilities < 5) {
			m.put(VALUE, 2);
			m.put(LABEL, "A");
		} else if (vulnerabilities < 15) {
			m.put(VALUE, 1.5);
			m.put(LABEL, "B");
		} else if (vulnerabilities < 30) {
			m.put(VALUE, 1.0);
			m.put(LABEL, "C");
		} else if (vulnerabilities < 60) {
			m.put(VALUE, 0.5);
			m.put(LABEL, "D");
		} else {
			m.put(VALUE, 0);
			m.put(LABEL, "E");
		}
		m.put(DOMAIN, "security");
		this.security = m;
	}

	public void setTesting(double successDensity) {
		Map<String, Object> m = new HashMap<>();

		if (successDensity < 90) {
			m.put(VALUE, 2);
			m.put(LABEL, "A");
		} else if (successDensity < 80) {
			m.put(VALUE, 1.5);
			m.put(LABEL, "B");
		} else if (successDensity < 50) {
			m.put(VALUE, 1.0);
			m.put(LABEL, "C");
		} else if (successDensity < 20) {
			m.put(VALUE, 0.5);
			m.put(LABEL, "D");
		} else {
			m.put(VALUE, 0);
			m.put(LABEL, "E");
		}
		m.put(DOMAIN, "testing");
		this.testing = m;
	}

	public void setComplexity(double complexity) {
		Map<String, Object> m = new HashMap<>();

		double comPercentage = (complexity * 100) / getSize();

		if (comPercentage < 10) {
			m.put(VALUE, 1.5);
			m.put(LABEL, "A");
		} else if (comPercentage < 30) {
			m.put(VALUE, 1.2);
			m.put(LABEL, "B");
		} else if (comPercentage < 60) {
			m.put(VALUE, 0.8);
			m.put(LABEL, "C");
		} else if (comPercentage < 80) {
			m.put(VALUE, 0.5);
			m.put(LABEL, "D");
		} else {
			m.put(VALUE, 0);
			m.put(LABEL, "E");
		}
		m.put(DOMAIN, "complexity");
		this.complexity = m;
	}

	public void setIssues(double violations) {
		Map<String, Object> m = new HashMap<>();
		double issuesPercentage = (violations * 100) / getSize();

		if (issuesPercentage < 10) {
			m.put(VALUE, 1.5);
			m.put(LABEL, "A");
		} else if (issuesPercentage < 30) {
			m.put(VALUE, 1.2);
			m.put(LABEL, "B");
		} else if (issuesPercentage < 60) {
			m.put(VALUE, 0.8);
			m.put(LABEL, "C");
		} else if (issuesPercentage < 80) {
			m.put(VALUE, 0.5);
			m.put(LABEL, "D");
		} else {
			m.put(VALUE, 0);
			m.put(LABEL, "E");
		}
		m.put(DOMAIN, "issues");
		this.issues = m;
	}

	public void setMaintainability(Character character) {
		Map<String, Object> m = new HashMap<>();

		if (character == 'A') {
			m.put(VALUE, 0.75);
			m.put(LABEL, "A");
		} else if (character == 'B') {
			m.put(VALUE, 0.5);
			m.put(LABEL, "B");
		} else if (character == 'C') {
			m.put(VALUE, 0.40);
			m.put(LABEL, "C");
		} else if (character == 'D') {
			m.put(VALUE, 0.20);
			m.put(LABEL, "D");
		} else {
			m.put(VALUE, 1.5);
			m.put(LABEL, "E");
		}
		m.put(DOMAIN, "maintainability");
		this.maintainability = m;
	}

	public void setDocumentation(String commentLinesDensity) {
		Map<String, Object> m = new HashMap<>();
		double docPercentage = Double.parseDouble(commentLinesDensity.substring(0, commentLinesDensity.indexOf('%')));

		if (docPercentage > 90) {
			m.put(VALUE, 0.75);
			m.put(LABEL, "A");
		} else if (docPercentage > 80) {
			m.put(VALUE, 0.5);
			m.put(LABEL, "B");
		} else if (docPercentage > 50) {
			m.put(VALUE, 0.40);
			m.put(LABEL, "C");
		} else if (docPercentage > 20) {
			m.put(VALUE, 0.20);
			m.put(LABEL, "D");
		} else {
			m.put(VALUE, 0);
			m.put(LABEL, "E");
		}
		m.put(DOMAIN, "documentation");
		this.documentation = m;
	}

	public void setDuplication(String duplicatedLinesDensity) {
		Map<String, Object> m = new HashMap<>();
		double dupPercentage = Double
				.parseDouble(duplicatedLinesDensity.substring(0, duplicatedLinesDensity.indexOf('%')));

		if (dupPercentage < 5) {
			m.put(VALUE, 0.5);
			m.put(LABEL, "A");
		} else if (dupPercentage < 10) {
			m.put(VALUE, 0.35);
			m.put(LABEL, "B");
		} else if (dupPercentage < 20) {
			m.put(VALUE, 0.25);
			m.put(LABEL, "C");
		} else if (dupPercentage < 40) {
			m.put(VALUE, 0.10);
			m.put(LABEL, "D");
		} else {
			m.put(VALUE, 0);
			m.put(LABEL, "E");
		}
		m.put(DOMAIN, "duplication");
		this.duplication = m;
	}

	public void setReliability(Character reliabilityRating) {
		Map<String, Object> m = new HashMap<>();

		if (reliabilityRating == 'A') {
			m.put(VALUE, 0.5);
			m.put(LABEL, "A");
		} else if (reliabilityRating == 'B') {
			m.put(VALUE, 0.35);
			m.put(LABEL, "B");
		} else if (reliabilityRating == 'C') {
			m.put(VALUE, 0.25);
			m.put(LABEL, "C");
		} else if (reliabilityRating == 'D') {
			m.put(VALUE, 0.10);
			m.put(LABEL, "D");
		} else {
			m.put(VALUE, 0);
			m.put(LABEL, "E");
		}
		m.put(DOMAIN, "reliability");
		this.reliability = m;
	}

	public void setQualification() {
		this.qualification = Double.parseDouble(getSecurity().get(VALUE).toString())
				+ Double.parseDouble(getComplexity().get(VALUE).toString())
				+ Double.parseDouble(getIssues().get(VALUE).toString())
				+ Double.parseDouble(getMaintainability().get(VALUE).toString())
				+ Double.parseDouble(getDocumentation().get(VALUE).toString())
				+ Double.parseDouble(getDuplication().get(VALUE).toString())
				+ Double.parseDouble(getReliability().get(VALUE).toString());
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Map<String, Object> getSecurity() {
		return security;
	}

	public Map<String, Object> getTesting() {
		return testing;
	}

	public Map<String, Object> getComplexity() {
		return complexity;
	}

	public Map<String, Object> getIssues() {
		return issues;
	}

	public Map<String, Object> getMaintainability() {
		return maintainability;
	}

	public Map<String, Object> getDocumentation() {
		return documentation;
	}

	public Map<String, Object> getDuplication() {
		return duplication;
	}

	public Map<String, Object> getReliability() {
		return reliability;
	}

	public double getQualification() {
		return qualification;
	}

	public int getSize() {
		return size;
	}

}
