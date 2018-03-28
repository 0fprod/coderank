package com.atos.coderank.entities;

import java.util.Date;

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

@Entity
@Table(name = "METRICS")
public class ProjectMetricsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_METRICS")
	@SequenceGenerator(name = "SQ_METRICS", sequenceName = "SQ_METRICS", allocationSize = 1)
	@Column(name = "METRICS_ID")
	private Long metricsId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_ID")
	private ProjectEntity project;

	@Column(name = "VERSION_DATE")
	private Date versionDate;

	// Complexity
	@Column(name = "COM_COMPLEXITY")
	private Integer com_complexity;

	@Column(name = "COM_CLASS_COMPLEXITY")
	private Double com_class_complexity;

	@Column(name = "COM_FILE_COMPLEXITY")
	private Double com_file_complexity;

	@Column(name = "COM_FUNCTION_COMPLEXITY")
	private Double com_function_complexity;

	// Size
	@Column(name = "SIZE_CLASSES")
	private Integer size_classes;

	@Column(name = "SIZE_FUNCTIONS")
	private Integer size_functions;

	@Column(name = "SIZE_DIRECTORIES")
	private Integer size_directories;

	@Column(name = "SIZE_FILES")
	private Integer size_files;

	@Column(name = "SIZE_LINES")
	private Integer size_lines;

	@Column(name = "SIZE_NCLOC")
	private Integer size_ncloc;

	// Tests
	@Column(name = "TEST_TESTS")
	private Integer test_tests;

	@Column(name = "TEST_COVERAGE")
	private Double test_coverage;

	@Column(name = "TEST_TEST_ERROR")
	private Double test_test_error;

	@Column(name = "TEST_TESTS_PASSED")
	private Double test_tests_passed;

	// Issues
	@Column(name = "ISSUES_VIOLATIONS")
	private Integer issues_violations;

	@Column(name = "ISSUES_OPEN_ISSUES")
	private Integer issues_open_issues;

	@Column(name = "ISSUES_CONFIRMED_ISSUES")
	private Integer issues_confirmed_issues;

	@Column(name = "ISSUES_REOPENED_ISSUES")
	private Integer issues_reopened_issues;

	// Duplications
	@Column(name = "DUP_DUPLICATED_LINES")
	private Integer dup_duplicated_lines;

	@Column(name = "DUP_DUPLICATED_LINES_DENSITY")
	private String dup_duplicated_lines_density;

	@Column(name = "DUP_DUPLICATED_BLOCK")
	private Integer dup_duplicated_block;

	@Column(name = "DUP_DUPLICATED_FILES")
	private Integer dup_duplicated_files;

	// Documentations
	@Column(name = "DOC_COMMENT_LINES")
	private Integer doc_comment_lines;

	@Column(name = "DOC_COMMENT_LINES_DENSITY")
	private String doc_comment_lines_density;

	// Maintainability
	@Column(name = "MAIN_CODE_SMELLS")
	private Integer main_code_smells;

	@Column(name = "MAIN_SQALE_INDEX")
	private String main_sqale_index; // Technical debt

	@Column(name = "MAIN_SQALE_RATING")
	private Character main_sqale_rating;

	// Reliability
	@Column(name = "REL_RELIABILITY_RATING")
	private Character rel_reliability_rating;

	@Column(name = "REL_BUGS")
	private Integer rel_bugs;

	@Column(name = "REL_RELIABILITY_REMEDIATION_EFFORT")
	private String rel_reliability_remediation_effort;

	// Security
	@Column(name = "SEC_VULNERABILITIES")
	private Integer sec_vulnerabilities;

	@Column(name = "SEC_SECURITY_RATING")
	private Character sec_security_rating;

	@Column(name = "SEC_SECURITY_REMEDIATION_EFFORT")
	private String sec_security_remediation_effort;

	// Passes
	@Column(name = "ALERT_STATUS")
	private String alert_status;

	public ProjectMetricsEntity() {

	}

	public ProjectMetricsEntity(Date versiondate, Integer com_complexity, Double com_class_complexity,
			Double com_file_complexity, Double com_function_complexity, Integer size_classes, Integer size_functions,
			Integer size_directories, Integer size_files, Integer size_lines, Integer size_ncloc, Integer test_tests,
			Double test_coverage, Double test_test_error, Double test_tests_passed, Integer issues_violations,
			Integer issues_open_issues, Integer issues_confirmed_issues, Integer issues_reopened_issues,
			Integer dup_duplicated_lines, String dup_duplicated_lines_density, Integer dup_duplicated_block,
			Integer dup_duplicated_files, Integer doc_comment_lines, String doc_comment_lines_density,
			Integer main_code_smells, String main_sqale_index, Character main_sqale_rating,
			Character rel_reliability_rating, Integer rel_bugs, String rel_reliability_remediation_effort,
			Integer sec_vulnerabilities, Character sec_security_rating, String sec_security_remediation_effort,
			String alert_status) {
		this.versionDate = versiondate;
		this.com_complexity = com_complexity;
		this.com_class_complexity = com_class_complexity;
		this.com_file_complexity = com_file_complexity;
		this.com_function_complexity = com_function_complexity;
		this.size_classes = size_classes;
		this.size_functions = size_functions;
		this.size_directories = size_directories;
		this.size_files = size_files;
		this.size_lines = size_lines;
		this.size_ncloc = size_ncloc;
		this.test_tests = test_tests;
		this.test_coverage = test_coverage;
		this.test_test_error = test_test_error;
		this.test_tests_passed = test_tests_passed;
		this.issues_violations = issues_violations;
		this.issues_open_issues = issues_open_issues;
		this.issues_confirmed_issues = issues_confirmed_issues;
		this.issues_reopened_issues = issues_reopened_issues;
		this.dup_duplicated_lines = dup_duplicated_lines;
		this.dup_duplicated_lines_density = dup_duplicated_lines_density;
		this.dup_duplicated_block = dup_duplicated_block;
		this.dup_duplicated_files = dup_duplicated_files;
		this.doc_comment_lines = doc_comment_lines;
		this.doc_comment_lines_density = doc_comment_lines_density;
		this.main_code_smells = main_code_smells;
		this.main_sqale_index = main_sqale_index;
		this.main_sqale_rating = main_sqale_rating;
		this.rel_reliability_rating = rel_reliability_rating;
		this.rel_bugs = rel_bugs;
		this.rel_reliability_remediation_effort = rel_reliability_remediation_effort;
		this.sec_vulnerabilities = sec_vulnerabilities;
		this.sec_security_rating = sec_security_rating;
		this.sec_security_remediation_effort = sec_security_remediation_effort;
		this.alert_status = alert_status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alert_status == null) ? 0 : alert_status.hashCode());
		result = prime * result + ((com_class_complexity == null) ? 0 : com_class_complexity.hashCode());
		result = prime * result + ((com_complexity == null) ? 0 : com_complexity.hashCode());
		result = prime * result + ((com_file_complexity == null) ? 0 : com_file_complexity.hashCode());
		result = prime * result + ((com_function_complexity == null) ? 0 : com_function_complexity.hashCode());
		result = prime * result + ((doc_comment_lines == null) ? 0 : doc_comment_lines.hashCode());
		result = prime * result + ((doc_comment_lines_density == null) ? 0 : doc_comment_lines_density.hashCode());
		result = prime * result + ((dup_duplicated_block == null) ? 0 : dup_duplicated_block.hashCode());
		result = prime * result + ((dup_duplicated_files == null) ? 0 : dup_duplicated_files.hashCode());
		result = prime * result + ((dup_duplicated_lines == null) ? 0 : dup_duplicated_lines.hashCode());
		result = prime * result
				+ ((dup_duplicated_lines_density == null) ? 0 : dup_duplicated_lines_density.hashCode());
		result = prime * result + ((issues_confirmed_issues == null) ? 0 : issues_confirmed_issues.hashCode());
		result = prime * result + ((issues_open_issues == null) ? 0 : issues_open_issues.hashCode());
		result = prime * result + ((issues_reopened_issues == null) ? 0 : issues_reopened_issues.hashCode());
		result = prime * result + ((issues_violations == null) ? 0 : issues_violations.hashCode());
		result = prime * result + ((main_code_smells == null) ? 0 : main_code_smells.hashCode());
		result = prime * result + ((main_sqale_index == null) ? 0 : main_sqale_index.hashCode());
		result = prime * result + ((main_sqale_rating == null) ? 0 : main_sqale_rating.hashCode());
		result = prime * result + ((metricsId == null) ? 0 : metricsId.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((rel_bugs == null) ? 0 : rel_bugs.hashCode());
		result = prime * result + ((rel_reliability_rating == null) ? 0 : rel_reliability_rating.hashCode());
		result = prime * result
				+ ((rel_reliability_remediation_effort == null) ? 0 : rel_reliability_remediation_effort.hashCode());
		result = prime * result + ((sec_security_rating == null) ? 0 : sec_security_rating.hashCode());
		result = prime * result
				+ ((sec_security_remediation_effort == null) ? 0 : sec_security_remediation_effort.hashCode());
		result = prime * result + ((sec_vulnerabilities == null) ? 0 : sec_vulnerabilities.hashCode());
		result = prime * result + ((size_classes == null) ? 0 : size_classes.hashCode());
		result = prime * result + ((size_directories == null) ? 0 : size_directories.hashCode());
		result = prime * result + ((size_files == null) ? 0 : size_files.hashCode());
		result = prime * result + ((size_functions == null) ? 0 : size_functions.hashCode());
		result = prime * result + ((size_lines == null) ? 0 : size_lines.hashCode());
		result = prime * result + ((size_ncloc == null) ? 0 : size_ncloc.hashCode());
		result = prime * result + ((test_coverage == null) ? 0 : test_coverage.hashCode());
		result = prime * result + ((test_test_error == null) ? 0 : test_test_error.hashCode());
		result = prime * result + ((test_tests == null) ? 0 : test_tests.hashCode());
		result = prime * result + ((test_tests_passed == null) ? 0 : test_tests_passed.hashCode());
		result = prime * result + ((versionDate == null) ? 0 : versionDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectMetricsEntity other = (ProjectMetricsEntity) obj;
		if (alert_status == null) {
			if (other.alert_status != null)
				return false;
		} else if (!alert_status.equals(other.alert_status))
			return false;
		if (com_class_complexity == null) {
			if (other.com_class_complexity != null)
				return false;
		} else if (!com_class_complexity.equals(other.com_class_complexity))
			return false;
		if (com_complexity == null) {
			if (other.com_complexity != null)
				return false;
		} else if (!com_complexity.equals(other.com_complexity))
			return false;
		if (com_file_complexity == null) {
			if (other.com_file_complexity != null)
				return false;
		} else if (!com_file_complexity.equals(other.com_file_complexity))
			return false;
		if (com_function_complexity == null) {
			if (other.com_function_complexity != null)
				return false;
		} else if (!com_function_complexity.equals(other.com_function_complexity))
			return false;
		if (doc_comment_lines == null) {
			if (other.doc_comment_lines != null)
				return false;
		} else if (!doc_comment_lines.equals(other.doc_comment_lines))
			return false;
		if (doc_comment_lines_density == null) {
			if (other.doc_comment_lines_density != null)
				return false;
		} else if (!doc_comment_lines_density.equals(other.doc_comment_lines_density))
			return false;
		if (dup_duplicated_block == null) {
			if (other.dup_duplicated_block != null)
				return false;
		} else if (!dup_duplicated_block.equals(other.dup_duplicated_block))
			return false;
		if (dup_duplicated_files == null) {
			if (other.dup_duplicated_files != null)
				return false;
		} else if (!dup_duplicated_files.equals(other.dup_duplicated_files))
			return false;
		if (dup_duplicated_lines == null) {
			if (other.dup_duplicated_lines != null)
				return false;
		} else if (!dup_duplicated_lines.equals(other.dup_duplicated_lines))
			return false;
		if (dup_duplicated_lines_density == null) {
			if (other.dup_duplicated_lines_density != null)
				return false;
		} else if (!dup_duplicated_lines_density.equals(other.dup_duplicated_lines_density))
			return false;
		if (issues_confirmed_issues == null) {
			if (other.issues_confirmed_issues != null)
				return false;
		} else if (!issues_confirmed_issues.equals(other.issues_confirmed_issues))
			return false;
		if (issues_open_issues == null) {
			if (other.issues_open_issues != null)
				return false;
		} else if (!issues_open_issues.equals(other.issues_open_issues))
			return false;
		if (issues_reopened_issues == null) {
			if (other.issues_reopened_issues != null)
				return false;
		} else if (!issues_reopened_issues.equals(other.issues_reopened_issues))
			return false;
		if (issues_violations == null) {
			if (other.issues_violations != null)
				return false;
		} else if (!issues_violations.equals(other.issues_violations))
			return false;
		if (main_code_smells == null) {
			if (other.main_code_smells != null)
				return false;
		} else if (!main_code_smells.equals(other.main_code_smells))
			return false;
		if (main_sqale_index == null) {
			if (other.main_sqale_index != null)
				return false;
		} else if (!main_sqale_index.equals(other.main_sqale_index))
			return false;
		if (main_sqale_rating == null) {
			if (other.main_sqale_rating != null)
				return false;
		} else if (!main_sqale_rating.equals(other.main_sqale_rating))
			return false;
		if (metricsId == null) {
			if (other.metricsId != null)
				return false;
		} else if (!metricsId.equals(other.metricsId))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (rel_bugs == null) {
			if (other.rel_bugs != null)
				return false;
		} else if (!rel_bugs.equals(other.rel_bugs))
			return false;
		if (rel_reliability_rating == null) {
			if (other.rel_reliability_rating != null)
				return false;
		} else if (!rel_reliability_rating.equals(other.rel_reliability_rating))
			return false;
		if (rel_reliability_remediation_effort == null) {
			if (other.rel_reliability_remediation_effort != null)
				return false;
		} else if (!rel_reliability_remediation_effort.equals(other.rel_reliability_remediation_effort))
			return false;
		if (sec_security_rating == null) {
			if (other.sec_security_rating != null)
				return false;
		} else if (!sec_security_rating.equals(other.sec_security_rating))
			return false;
		if (sec_security_remediation_effort == null) {
			if (other.sec_security_remediation_effort != null)
				return false;
		} else if (!sec_security_remediation_effort.equals(other.sec_security_remediation_effort))
			return false;
		if (sec_vulnerabilities == null) {
			if (other.sec_vulnerabilities != null)
				return false;
		} else if (!sec_vulnerabilities.equals(other.sec_vulnerabilities))
			return false;
		if (size_classes == null) {
			if (other.size_classes != null)
				return false;
		} else if (!size_classes.equals(other.size_classes))
			return false;
		if (size_directories == null) {
			if (other.size_directories != null)
				return false;
		} else if (!size_directories.equals(other.size_directories))
			return false;
		if (size_files == null) {
			if (other.size_files != null)
				return false;
		} else if (!size_files.equals(other.size_files))
			return false;
		if (size_functions == null) {
			if (other.size_functions != null)
				return false;
		} else if (!size_functions.equals(other.size_functions))
			return false;
		if (size_lines == null) {
			if (other.size_lines != null)
				return false;
		} else if (!size_lines.equals(other.size_lines))
			return false;
		if (size_ncloc == null) {
			if (other.size_ncloc != null)
				return false;
		} else if (!size_ncloc.equals(other.size_ncloc))
			return false;
		if (test_coverage == null) {
			if (other.test_coverage != null)
				return false;
		} else if (!test_coverage.equals(other.test_coverage))
			return false;
		if (test_test_error == null) {
			if (other.test_test_error != null)
				return false;
		} else if (!test_test_error.equals(other.test_test_error))
			return false;
		if (test_tests == null) {
			if (other.test_tests != null)
				return false;
		} else if (!test_tests.equals(other.test_tests))
			return false;
		if (test_tests_passed == null) {
			if (other.test_tests_passed != null)
				return false;
		} else if (!test_tests_passed.equals(other.test_tests_passed))
			return false;
		if (versionDate == null) {
			if (other.versionDate != null)
				return false;
		} else if (!versionDate.equals(other.versionDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProjectMetricsEntity [metricsId=" + metricsId + ", versiondate=" + versionDate + ", com_complexity="
				+ com_complexity + ", com_class_complexity=" + com_class_complexity + ", com_file_complexity="
				+ com_file_complexity + ", com_function_complexity=" + com_function_complexity + ", size_classes="
				+ size_classes + ", size_functions=" + size_functions + ", size_directories=" + size_directories
				+ ", size_files=" + size_files + ", size_lines=" + size_lines + ", size_ncloc=" + size_ncloc
				+ ", test_tests=" + test_tests + ", test_coverage=" + test_coverage + ", test_test_error="
				+ test_test_error + ", test_tests_passed=" + test_tests_passed + ", issues_violations="
				+ issues_violations + ", issues_open_issues=" + issues_open_issues + ", issues_confirmed_issues="
				+ issues_confirmed_issues + ", issues_reopened_issues=" + issues_reopened_issues
				+ ", dup_duplicated_lines=" + dup_duplicated_lines + ", dup_duplicated_lines_density="
				+ dup_duplicated_lines_density + ", dup_duplicated_block=" + dup_duplicated_block
				+ ", dup_duplicated_files=" + dup_duplicated_files + ", doc_comment_lines=" + doc_comment_lines
				+ ", doc_comment_lines_density=" + doc_comment_lines_density + ", main_code_smells=" + main_code_smells
				+ ", main_sqale_index=" + main_sqale_index + ", main_sqale_rating=" + main_sqale_rating
				+ ", rel_reliability_rating=" + rel_reliability_rating + ", rel_bugs=" + rel_bugs
				+ ", rel_reliability_remediation_effort=" + rel_reliability_remediation_effort
				+ ", sec_vulnerabilities=" + sec_vulnerabilities + ", sec_security_rating=" + sec_security_rating
				+ ", sec_security_remediation_effort=" + sec_security_remediation_effort + ", alert_status="
				+ alert_status + "]";
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

	public Date getVersiondate() {
		return versionDate;
	}

	public void setVersiondate(Date versiondate) {
		this.versionDate = versiondate;
	}

	public Integer getCom_complexity() {
		return com_complexity;
	}

	public void setCom_complexity(Integer com_complexity) {
		this.com_complexity = com_complexity;
	}

	public Double getCom_class_complexity() {
		return com_class_complexity;
	}

	public void setCom_class_complexity(Double com_class_complexity) {
		this.com_class_complexity = com_class_complexity;
	}

	public Double getCom_file_complexity() {
		return com_file_complexity;
	}

	public void setCom_file_complexity(Double com_file_complexity) {
		this.com_file_complexity = com_file_complexity;
	}

	public Double getCom_function_complexity() {
		return com_function_complexity;
	}

	public void setCom_function_complexity(Double com_function_complexity) {
		this.com_function_complexity = com_function_complexity;
	}

	public Integer getSize_classes() {
		return size_classes;
	}

	public void setSize_classes(Integer size_classes) {
		this.size_classes = size_classes;
	}

	public Integer getSize_functions() {
		return size_functions;
	}

	public void setSize_functions(Integer size_functions) {
		this.size_functions = size_functions;
	}

	public Integer getSize_directories() {
		return size_directories;
	}

	public void setSize_directories(Integer size_directories) {
		this.size_directories = size_directories;
	}

	public Integer getSize_files() {
		return size_files;
	}

	public void setSize_files(Integer size_files) {
		this.size_files = size_files;
	}

	public Integer getSize_lines() {
		return size_lines;
	}

	public void setSize_lines(Integer size_lines) {
		this.size_lines = size_lines;
	}

	public Integer getSize_ncloc() {
		return size_ncloc;
	}

	public void setSize_ncloc(Integer size_ncloc) {
		this.size_ncloc = size_ncloc;
	}

	public Integer getTest_tests() {
		return test_tests;
	}

	public void setTest_tests(Integer test_tests) {
		this.test_tests = test_tests;
	}

	public Double getTest_coverage() {
		return test_coverage;
	}

	public void setTest_coverage(Double test_coverage) {
		this.test_coverage = test_coverage;
	}

	public Double getTest_test_error() {
		return test_test_error;
	}

	public void setTest_test_error(Double test_test_error) {
		this.test_test_error = test_test_error;
	}

	public Double getTest_tests_passed() {
		return test_tests_passed;
	}

	public void setTest_tests_passed(Double test_tests_passed) {
		this.test_tests_passed = test_tests_passed;
	}

	public Integer getIssues_violations() {
		return issues_violations;
	}

	public void setIssues_violations(Integer issues_violations) {
		this.issues_violations = issues_violations;
	}

	public Integer getIssues_open_issues() {
		return issues_open_issues;
	}

	public void setIssues_open_issues(Integer issues_open_issues) {
		this.issues_open_issues = issues_open_issues;
	}

	public Integer getIssues_confirmed_issues() {
		return issues_confirmed_issues;
	}

	public void setIssues_confirmed_issues(Integer issues_confirmed_issues) {
		this.issues_confirmed_issues = issues_confirmed_issues;
	}

	public Integer getIssues_reopened_issues() {
		return issues_reopened_issues;
	}

	public void setIssues_reopened_issues(Integer issues_reopened_issues) {
		this.issues_reopened_issues = issues_reopened_issues;
	}

	public Integer getDup_duplicated_lines() {
		return dup_duplicated_lines;
	}

	public void setDup_duplicated_lines(Integer dup_duplicated_lines) {
		this.dup_duplicated_lines = dup_duplicated_lines;
	}

	public String getDup_duplicated_lines_density() {
		return dup_duplicated_lines_density;
	}

	public void setDup_duplicated_lines_density(String dup_duplicated_lines_density) {
		this.dup_duplicated_lines_density = dup_duplicated_lines_density;
	}

	public Integer getDup_duplicated_block() {
		return dup_duplicated_block;
	}

	public void setDup_duplicated_block(Integer dup_duplicated_block) {
		this.dup_duplicated_block = dup_duplicated_block;
	}

	public Integer getDup_duplicated_files() {
		return dup_duplicated_files;
	}

	public void setDup_duplicated_files(Integer dup_duplicated_files) {
		this.dup_duplicated_files = dup_duplicated_files;
	}

	public Integer getDoc_comment_lines() {
		return doc_comment_lines;
	}

	public void setDoc_comment_lines(Integer doc_comment_lines) {
		this.doc_comment_lines = doc_comment_lines;
	}

	public String getDoc_comment_lines_density() {
		return doc_comment_lines_density;
	}

	public void setDoc_comment_lines_density(String doc_comment_lines_density) {
		this.doc_comment_lines_density = doc_comment_lines_density;
	}

	public Integer getMain_code_smells() {
		return main_code_smells;
	}

	public void setMain_code_smells(Integer main_code_smells) {
		this.main_code_smells = main_code_smells;
	}

	public String getMain_sqale_index() {
		return main_sqale_index;
	}

	public void setMain_sqale_index(String main_sqale_index) {
		this.main_sqale_index = main_sqale_index;
	}

	public Character getMain_sqale_rating() {
		return main_sqale_rating;
	}

	public void setMain_sqale_rating(Character main_sqale_rating) {
		this.main_sqale_rating = main_sqale_rating;
	}

	public Character getRel_reliability_rating() {
		return rel_reliability_rating;
	}

	public void setRel_reliability_rating(Character rel_reliability_rating) {
		this.rel_reliability_rating = rel_reliability_rating;
	}

	public Integer getRel_bugs() {
		return rel_bugs;
	}

	public void setRel_bugs(Integer rel_bugs) {
		this.rel_bugs = rel_bugs;
	}

	public String getRel_reliability_remediation_effort() {
		return rel_reliability_remediation_effort;
	}

	public void setRel_reliability_remediation_effort(String rel_reliability_remediation_effort) {
		this.rel_reliability_remediation_effort = rel_reliability_remediation_effort;
	}

	public Integer getSec_vulnerabilities() {
		return sec_vulnerabilities;
	}

	public void setSec_vulnerabilities(Integer sec_vulnerabilities) {
		this.sec_vulnerabilities = sec_vulnerabilities;
	}

	public Character getSec_security_rating() {
		return sec_security_rating;
	}

	public void setSec_security_rating(Character sec_security_rating) {
		this.sec_security_rating = sec_security_rating;
	}

	public String getSec_security_remediation_effort() {
		return sec_security_remediation_effort;
	}

	public void setSec_security_remediation_effort(String sec_security_remediation_effort) {
		this.sec_security_remediation_effort = sec_security_remediation_effort;
	}

	public String getAlert_status() {
		return alert_status;
	}

	public void setAlert_status(String alert_status) {
		this.alert_status = alert_status;
	}

}
