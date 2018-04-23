package com.atos.coderank.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "METRICS")
public class ProjectMetricsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_METRICS")
	@SequenceGenerator(name = "SQ_METRICS", sequenceName = "SQ_METRICS", allocationSize = 1)
	@Column(name = "METRICS_ID")
	private Long metricsId;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_ID")
	private ProjectEntity project;

	@Column(name = "VERSION_DATE")
	private Date versionDate;

	// Complexity
	@Column(name = "COMPLEXITY")
	private Integer comComplexity;

	@Column(name = "CLASS_COMPLEXITY")
	private Double comClassComplexity;

	@Column(name = "FILE_COMPLEXITY")
	private Double comFileComplexity;

	@Column(name = "FUNCTION_COMPLEXITY")
	private Double comFunctionComplexity;

	// Size
	@Column(name = "CLASSES")
	private Integer sizClasses;

	@Column(name = "FUNCTIONS")
	private Integer sizFunctions;

	@Column(name = "DIRECTORIES")
	private Integer sizDirectories;

	@Column(name = "FILES")
	private Integer sizFiles;

	@Column(name = "LINES")
	private Integer sizLines;

	@Column(name = "NCLOC")
	private Integer sizNcloc;

	// Tests
	@Column(name = "TESTS")
	private Integer tesTests;

	@Column(name = "COVERAGE")
	private Double tesCoverage;

	// Issues
	@Column(name = "VIOLATIONS")
	private Integer issViolations;

	@Column(name = "OPEN_ISSUES")
	private Integer issOpenIssues;

	@Column(name = "MINOR_VIOLATIONS")
	private Integer issMinorViolations;

	@Column(name = "MAJOR_VIOLATIONS")
	private Integer issMajorViolations;

	// Duplications
	@Column(name = "DUPLICATED_LINES")
	private Integer dupDuplicatedLines;

	@Column(name = "DUPLICATED_LINES_DENSITY")
	private String dupDuplicatedLinesDensity;

	@Column(name = "DUPLICATED_BLOCK")
	private Integer dupDuplicatedBlocks;

	@Column(name = "DUPLICATED_FILES")
	private Integer dupDuplicatedFiles;

	// Documentations
	@Column(name = "COMMENT_LINES")
	private Integer docCommentLines;

	@Column(name = "COMMENT_LINES_DENSITY")
	private String docCommentLinesDensity;

	// Maintainability
	@Column(name = "CODE_SMELLS")
	private Integer maiCodeSmells;

	@Column(name = "SQALE_RATING")
	private Character mainSqaleRating;

	// Reliability
	@Column(name = "RELIABILITY_RATING")
	private Character relReliabilityRating;

	@Column(name = "BUGS")
	private Integer relBugs;

	// Security
	@Column(name = "VULNERABILITIES")
	private Integer secVulnerabilities;

	@Column(name = "SECURITY_RATING")
	private Character secSecurityRating;

	// Passes
	@Column(name = "ALERT_STATUS")
	private String alertStatus;

	@Transient
	private Map<String, List<String>> domains;

	public ProjectMetricsEntity() {
		// Initialize domain with metricKeys
		this.domains = new HashMap<>();
		this.initComplexity();
		this.initSize();
		this.initTest();
		this.initIssues();
		this.initDuplications();
		this.initDocumentation();
		this.initMaintainability();
		this.initReliability();
		this.initSecurity();
		this.initAlertStatus();

	}

	private void initComplexity() {
		List<String> list = new ArrayList<>(
				Arrays.asList("complexity", "class_complexity", "file_complexity", "function_complexity"));
		this.domains.put("complexity", list);
	}

	private void initSize() {
		List<String> list = new ArrayList<>(
				Arrays.asList("classes", ",functions", "directories", "files", "lines", "ncloc"));
		this.domains.put("size", list);
	}

	private void initTest() {
		List<String> list = new ArrayList<>(Arrays.asList("tests", "coverage"));
		this.domains.put("tests", list);
	}

	private void initIssues() {
		List<String> list = new ArrayList<>(
				Arrays.asList("violations", "open_issues", "minor_violations", "major_violations"));
		this.domains.put("issues", list);
	}

	private void initDuplications() {
		List<String> list = new ArrayList<>(
				Arrays.asList("duplicated_lines", "duplicated_lines_density", "duplicated_blocks","duplicated_files"));
		this.domains.put("duplications", list);
	}

	private void initDocumentation() {
		List<String> list = new ArrayList<>(Arrays.asList("comment_lines", "comment_lines_density"));
		this.domains.put("documentation", list);
	}

	private void initMaintainability() {
		List<String> list = new ArrayList<>(Arrays.asList("code_smells", "sqale_rating"));
		this.domains.put("maintainability", list);
	}

	private void initReliability() {
		List<String> list = new ArrayList<>(Arrays.asList("reliability_rating", "bugs"));
		this.domains.put("reliability", list);
	}

	private void initSecurity() {
		List<String> list = new ArrayList<>(Arrays.asList("vulnerabilities", "security_rating"));
		this.domains.put("security", list);
	}

	private void initAlertStatus() {
		List<String> list = new ArrayList<>(Arrays.asList("alert_status"));
		this.domains.put("passed", list);
	}

	@Override
	public String toString() {
		return "ProjectMetricsEntity [metricsId=" + metricsId + ", versionDate=" + versionDate + ", comComplexity="
				+ comComplexity + ", comClassComplexity=" + comClassComplexity + ", comFileComplexity="
				+ comFileComplexity + ", comFunctionComplexity=" + comFunctionComplexity + ", sizClasses=" + sizClasses
				+ ", sizFunctions=" + sizFunctions + ", sizDirectories=" + sizDirectories + ", sizFiles=" + sizFiles
				+ ", sizLines=" + sizLines + ", sizNcloc=" + sizNcloc + ", tesTests=" + tesTests + ", tesCoverage="
				+ tesCoverage + ", issViolations=" + issViolations + ", issOpenIssues=" + issOpenIssues
				+ ", issMinorViolations=" + issMinorViolations + ", issMajorViolations=" + issMajorViolations
				+ ", dupDuplicatedLines=" + dupDuplicatedLines + ", dupDuplicatedLinesDensity="
				+ dupDuplicatedLinesDensity + ", dupDuplicatedBlocks=" + dupDuplicatedBlocks + ", dupDuplicatedFiles="
				+ dupDuplicatedFiles + ", docCommentLines=" + docCommentLines + ", docCommentLinesDensity="
				+ docCommentLinesDensity + ", maiCodeSmells=" + maiCodeSmells + ", mainSqaleRating=" + mainSqaleRating
				+ ", relReliabilityRating=" + relReliabilityRating + ", relBugs=" + relBugs + ", secVulnerabilities="
				+ secVulnerabilities + ", secSecurityRating=" + secSecurityRating + ", alertStatus=" + alertStatus
				+ "]";
	}

	public Long getMetricsId() {
		return metricsId;
	}

	public void setMetricsId(Long metricsId) {
		this.metricsId = metricsId;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

	public Date getVersionDate() {
		return versionDate;
	}

	public void setVersionDate(Date versionDate) {
		this.versionDate = versionDate;
	}

	public Integer getComComplexity() {
		return comComplexity;
	}

	public void setComComplexity(Integer comComplexity) {
		this.comComplexity = comComplexity;
	}

	public Double getComClassComplexity() {
		return comClassComplexity;
	}

	public void setComClassComplexity(Double comClassComplexity) {
		this.comClassComplexity = comClassComplexity;
	}

	public Double getComFileComplexity() {
		return comFileComplexity;
	}

	public void setComFileComplexity(Double comFileComplexity) {
		this.comFileComplexity = comFileComplexity;
	}

	public Double getComFunctionComplexity() {
		return comFunctionComplexity;
	}

	public void setComFunctionComplexity(Double comFunctionComplexity) {
		this.comFunctionComplexity = comFunctionComplexity;
	}

	public Integer getSizClasses() {
		return sizClasses;
	}

	public void setSizClasses(Integer sizClasses) {
		this.sizClasses = sizClasses;
	}

	public Integer getSizFunctions() {
		return sizFunctions;
	}

	public void setSizFunctions(Integer sizFunctions) {
		this.sizFunctions = sizFunctions;
	}

	public Integer getSizDirectories() {
		return sizDirectories;
	}

	public void setSizDirectories(Integer sizDirectories) {
		this.sizDirectories = sizDirectories;
	}

	public Integer getSizFiles() {
		return sizFiles;
	}

	public void setSizFiles(Integer sizFiles) {
		this.sizFiles = sizFiles;
	}

	public Integer getSizLines() {
		return sizLines;
	}

	public void setSizLines(Integer sizLines) {
		this.sizLines = sizLines;
	}

	public Integer getSizNcloc() {
		return sizNcloc;
	}

	public void setSizNcloc(Integer sizNcloc) {
		this.sizNcloc = sizNcloc;
	}

	public Integer getTesTests() {
		return tesTests;
	}

	public void setTesTests(Integer tesTests) {
		this.tesTests = tesTests;
	}

	public Double getTesCoverage() {
		return tesCoverage;
	}

	public void setTesCoverage(Double tesCoverage) {
		this.tesCoverage = tesCoverage;
	}

	public Integer getIssViolations() {
		return issViolations;
	}

	public void setIssViolations(Integer issViolations) {
		this.issViolations = issViolations;
	}

	public Integer getIssOpenIssues() {
		return issOpenIssues;
	}

	public void setIssOpenIssues(Integer issOpenIssues) {
		this.issOpenIssues = issOpenIssues;
	}

	public Integer getIssMinorViolations() {
		return issMinorViolations;
	}

	public void setIssMinorViolations(Integer issMinorViolations) {
		this.issMinorViolations = issMinorViolations;
	}

	public Integer getIssMajorViolations() {
		return issMajorViolations;
	}

	public void setIssMajorIssues(Integer issMajorViolations) {
		this.issMajorViolations = issMajorViolations;
	}

	public Integer getDupDuplicatedLines() {
		return dupDuplicatedLines;
	}

	public void setDupDuplicatedLines(Integer dupDuplicatedLines) {
		this.dupDuplicatedLines = dupDuplicatedLines;
	}

	public String getDupDuplicatedLinesDensity() {
		return dupDuplicatedLinesDensity;
	}

	public void setDupDuplicatedLinesDensity(String dupDuplicatedLinesDensity) {
		this.dupDuplicatedLinesDensity = dupDuplicatedLinesDensity;
	}

	public Integer getDupDuplicatedBlocks() {
		return dupDuplicatedBlocks;
	}

	public void setDupDuplicatedBlocks(Integer dupDuplicatedBlocks) {
		this.dupDuplicatedBlocks = dupDuplicatedBlocks;
	}

	public Integer getDupDuplicatedFiles() {
		return dupDuplicatedFiles;
	}

	public void setDupDuplicatedFiles(Integer dupDuplicatedFiles) {
		this.dupDuplicatedFiles = dupDuplicatedFiles;
	}

	public Integer getDocCommentLines() {
		return docCommentLines;
	}

	public void setDocCommentLines(Integer docCommentLines) {
		this.docCommentLines = docCommentLines;
	}

	public String getDocCommentLinesDensity() {
		return docCommentLinesDensity;
	}

	public void setDocCommentLinesDensity(String docCommentLinesDensity) {
		this.docCommentLinesDensity = docCommentLinesDensity;
	}

	public Integer getMaiCodeSmells() {
		return maiCodeSmells;
	}

	public void setMaiCodeSmells(Integer maiCodeSmells) {
		this.maiCodeSmells = maiCodeSmells;
	}

	public Character getMainSqaleRating() {
		return mainSqaleRating;
	}

	public void setMainSqaleRating(Character mainSqaleRating) {
		this.mainSqaleRating = mainSqaleRating;
	}

	public Character getRelReliabilityRating() {
		return relReliabilityRating;
	}

	public void setRelReliabilityRating(Character relReliabilityRating) {
		this.relReliabilityRating = relReliabilityRating;
	}

	public Integer getRelBugs() {
		return relBugs;
	}

	public void setRelBugs(Integer relBugs) {
		this.relBugs = relBugs;
	}

	public Integer getSecVulnerabilities() {
		return secVulnerabilities;
	}

	public void setSecVulnerabilities(Integer secVulnerabilities) {
		this.secVulnerabilities = secVulnerabilities;
	}

	public Character getSecSecurityRating() {
		return secSecurityRating;
	}

	public void setSecSecurityRating(Character secSecurityRating) {
		this.secSecurityRating = secSecurityRating;
	}

	public String getAlertStatus() {
		return alertStatus;
	}

	public void setAlertStatus(String alertStatus) {
		this.alertStatus = alertStatus;
	}

	public Map<String, List<String>> getDomains() {
		return domains;
	}

}
