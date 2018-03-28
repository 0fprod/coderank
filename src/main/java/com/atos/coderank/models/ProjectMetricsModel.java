package com.atos.coderank.models;

import java.util.Date;

public class ProjectMetricsModel {

	private Long metricsId;
	private ProjectModel project;
	private Date versionDate;
	private Integer com_complexity;
	private Double com_class_complexity;
	private Double com_file_complexity;
	private Double com_function_complexity;
	private Integer size_classes;
	private Integer size_functions;
	private Integer size_directories;
	private Integer size_files;
	private Integer size_lines;
	private Integer size_ncloc;
	private Integer test_tests;
	private Double test_coverage;
	private Double test_test_error;
	private Double test_tests_passed;
	private Integer issues_violations;
	private Integer issues_open_issues;
	private Integer issues_confirmed_issues;
	private Integer issues_reopened_issues;
	private Integer dup_duplicated_lines;
	private String dup_duplicated_lines_density;
	private Integer dup_duplicated_block;
	private Integer dup_duplicated_files;
	private Integer doc_comment_lines;
	private String doc_comment_lines_density;
	private Integer main_code_smells;
	private String main_sqale_index;
	private Character main_sqale_rating;
	private Character rel_reliability_rating;
	private Integer rel_bugs;
	private String rel_reliability_remediation_effort;
	private Integer sec_vulnerabilities;
	private Character sec_security_rating;
	private String sec_security_remediation_effort;
	private String alert_status;

	public ProjectMetricsModel() {

	}

	public Long getMetricsId() {
		return metricsId;
	}

	public void setMetricsId(Long metricsId) {
		this.metricsId = metricsId;
	}

	public ProjectModel getProject() {
		return project;
	}

	public void setProject(ProjectModel project) {
		this.project = project;
	}

	public Date getVersionDate() {
		return versionDate;
	}

	public void setVersionDate(Date versionDate) {
		this.versionDate = versionDate;
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
