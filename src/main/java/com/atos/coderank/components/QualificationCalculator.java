package com.atos.coderank.components;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.atos.coderank.entities.ProjectMetricsEntity;

@Component("QualificationCalculator")
public class QualificationCalculator {

	private static final String VALUE = "value";
	private static final String LABEL = "label";
	private static final String DOMAIN = "domain";

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

	public QualificationCalculator() {
		// Sonar lint requirement
	}

	public void calcMetrics(ProjectMetricsEntity pme) {
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
	 * Returns the attribute of then given index
	 * 
	 * @param i
	 * @return
	 */
	public Map<String, Object> getAttribute(int i) {
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
			return null;
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
