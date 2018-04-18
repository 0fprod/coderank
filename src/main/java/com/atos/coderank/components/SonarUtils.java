package com.atos.coderank.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.atos.coderank.entities.GroupEntity;
import com.atos.coderank.entities.ProjectEntity;
import com.atos.coderank.entities.ProjectMetricsEntity;
import com.atos.coderank.entities.UserEntity;

/**
 * 
 * @author A679647 http://192.168.99.100:32768/web_api/
 */
@Component("sonarUtils")
public class SonarUtils {

	private static final String SONAR_API = "http://192.168.99.100:9001/api/";
	private static final String SONAR_URL = "http://192.168.99.100:9001/dashboard";
	private static final Log LOG = LogFactory.getLog(SonarUtils.class);
	private static final String METRIC = "metric";
	private static final String VALUE = "value";

	@Autowired
	@Qualifier("utilsComponent")
	private UtilsComponent uc;

	/**
	 * GET /user_groups/search
	 */
	public List<GroupEntity> findAllGroups() {
		RestTemplate rt = new RestTemplate();
		String url = SONAR_API + "/user_groups/search";
		List<GroupEntity> list = new ArrayList<>();

		try {
			ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET, new HttpEntity<String>(getHeaders()),
					String.class);

			JSONObject resjson = new JSONObject(response.getBody());
			JSONArray jsongroups = resjson.getJSONArray("groups");

			for (int i = 0; i < jsongroups.length(); i++) {
				JSONObject jsongroup = jsongroups.getJSONObject(i);
				GroupEntity group = new GroupEntity();
				group.setName(jsongroup.getString("name"));
				group.setDescription(jsongroup.getString("description"));
				list.add(group);
			}

		} catch (JSONException e) {
			LOG.error("Error fiding all groups -> " + e.getMessage());
		}

		return list;
	}

	/**
	 * GET /user_groups/search?q={group-name}
	 */
	public void findOneGroup() {
		// This method is not implemented yet
	}

	public void createGroup() {
		// This method is not implemented yet
	}

	public void deleteGroup() {
		// This method is not implemented yet
	}

	/**
	 * GET /users/search
	 * 
	 * @return
	 */
	public List<UserEntity> findAllUsers() {
		RestTemplate rt = new RestTemplate();
		String url = SONAR_API + "/users/search";
		List<UserEntity> list = new ArrayList<>();

		try {
			ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET, new HttpEntity<String>(getHeaders()),
					String.class);

			JSONObject jsonResponse = new JSONObject(response.getBody());
			JSONArray jsonUsers = jsonResponse.getJSONArray("users");

			for (int i = 0; i < jsonUsers.length(); i++) {
				JSONObject jsonUser = jsonUsers.getJSONObject(i);
				UserEntity user = new UserEntity();
				user.setDas(jsonUser.getString("login").toUpperCase());
				user.setName(jsonUser.getString("name"));
				user.setEmail(jsonUser.getString("email"));
				List<GroupEntity> groups = new ArrayList<>();
				JSONArray jsonGroups = jsonUser.getJSONArray("groups");

				for (int k = 0; k < jsonGroups.length(); k++) {
					String jsonGroup = jsonGroups.getString(k);
					GroupEntity group = new GroupEntity();
					group.setName(jsonGroup);
					groups.add(group);
				}

				user.setGroups(groups);
				list.add(user);
			}

		} catch (JSONException e) {
			LOG.error("Error fiding all users -> " + e.getMessage());
		}

		return list;
	}

	/**
	 * GET users/groups?login={das}
	 */
	public void findOneUser() {
		// This method is not implemented yet
	}

	public void createUser() {
		// This method is not implemented yet
	}

	public void deleteUser() {
		// This method is not implemented yet
	}

	/**
	 * GET /projects/search
	 */
	public List<ProjectEntity> findAllProjects() {
		RestTemplate rt = new RestTemplate();
		String url = SONAR_API + "/projects/search";
		List<ProjectEntity> list = new ArrayList<>();

		try {
			ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET, new HttpEntity<String>(getHeaders()),
					String.class);

			JSONObject jsonResponse = new JSONObject(response.getBody());
			JSONArray jsonProjects = jsonResponse.getJSONArray("components");

			for (int i = 0; i < jsonProjects.length(); i++) {
				JSONObject jsonProject = jsonProjects.getJSONObject(i);
				ProjectEntity project = new ProjectEntity();

				project.setProjectId(jsonProject.getString("id"));
				project.setName(jsonProject.getString("name"));
				project.setKey(jsonProject.getString("key"));
				project.setUrl(SONAR_URL + "?id=" + project.getKey());
				list.add(project);
			}

		} catch (JSONException e) {
			LOG.error("Error fiding all projects -> " + e.getMessage());
		}
		return list;
	}

	/**
	 * ProjectKey convention -> groupname:projectname GET
	 * /projects/search?projects={project-key}
	 */
	public void findOneProject() {
		// This method is not implemented yet
	}

	public void createProject() {
		// This method is not implemented yet
	}

	public void deleteProject() {
		// This method is not implemented yet
	}

	/**
	 * GET
	 * api/measures/component?component={project-key}&metricKeys={nclock,complexity,violations..etc}
	 */
	public ProjectMetricsEntity scanOneProject(String projectkey) {

		ProjectMetricsEntity pme = new ProjectMetricsEntity();
		List<String> metrics = this.uc.getAllMetricKeys(pme.getDomains());

		RestTemplate rt = new RestTemplate();
		String url1 = SONAR_API + "measures/component?component=" + projectkey + "&metricKeys="	+ this.uc.StringListToCsv(metrics);
		String url2 = SONAR_API + "projects/search?projects=" + projectkey;
		
		try {
			ResponseEntity<String> responseMetrics = rt.exchange(url1, HttpMethod.GET, new HttpEntity<String>(getHeaders()), String.class);
			ResponseEntity<String> responseProject = rt.exchange(url2, HttpMethod.GET, new HttpEntity<String>(getHeaders()), String.class);
			
			JSONObject jsonResponseProject = new JSONObject(responseProject.getBody());
			pme.setVersionDate(this.uc.parseStringToDate(jsonResponseProject.getJSONArray("components").getJSONObject(0).getString("lastAnalysisDate")));			

			JSONObject jsonResponseMetrics = new JSONObject(responseMetrics.getBody());
			JSONArray jsonMeasures = jsonResponseMetrics.getJSONArray("measures");

			for (int i = 0; i < jsonMeasures.length(); i++) {
				JSONObject jsonMetrics = jsonMeasures.getJSONObject(i);

				switch (jsonMetrics.getString(METRIC)) {
				case "complexity": // Complexity
					pme.setComComplexity(jsonMetrics.getInt(VALUE));
					break;
				case "class_complexity":
					pme.setComClassComplexity(jsonMetrics.getDouble(VALUE));
					break;
				case "file_complexity":
					pme.setComFileComplexity(jsonMetrics.getDouble(VALUE));
					break;
				case "function_complexity":
					pme.setComFunctionComplexity(jsonMetrics.getDouble(VALUE));
					break;
				case "classes": // Size
					pme.setSizClasses(jsonMetrics.getInt(VALUE));
					break;
				case "functions":
					pme.setSizFunctions(jsonMetrics.getInt(VALUE));
					break;
				case "directories":
					pme.setSizDirectories(jsonMetrics.getInt(VALUE));
					break;
				case "files":
					pme.setSizFiles(jsonMetrics.getInt(VALUE));
					break;
				case "lines":
					pme.setSizLines(jsonMetrics.getInt(VALUE));
					break;
				case "ncloc":
					pme.setSizNcloc(jsonMetrics.getInt(VALUE));
					break;
				case "tests": //Tests
					pme.setTesTests(jsonMetrics.getInt(VALUE));
					break;
				case "coverage":
					pme.setTesCoverage(jsonMetrics.getDouble(VALUE));
					break;
				case "violations":  //Issues
					pme.setIssViolations(jsonMetrics.getInt(VALUE));
					break;
				case "open_issues":
					pme.setIssOpenIssues(jsonMetrics.getInt(VALUE));
					break;
				case "minor_violations":
					pme.setIssMinorViolations(jsonMetrics.getInt(VALUE));
					break;
				case "major_violations":
					pme.setIssMajorIssues(jsonMetrics.getInt(VALUE));
					break;
				case "duplicated_lines": // Duplication
					pme.setDupDuplicatedLines(jsonMetrics.getInt(VALUE));
					break;
				case "duplicated_lines_density":
					pme.setDupDuplicatedLinesDensity(jsonMetrics.getString(VALUE) + "%");
					break;
				case "duplicated_blocks":
					pme.setDupDuplicatedBlocks(jsonMetrics.getInt(VALUE));
					break;
				case "comment_lines": // Documentation
					pme.setDocCommentLines(jsonMetrics.getInt(VALUE));
					break;
				case "comment_lines_density":
					pme.setDocCommentLinesDensity(jsonMetrics.getString(VALUE) + "%");
					break;
				case "code_smells": // Maintainability
					pme.setMaiCodeSmells(jsonMetrics.getInt(VALUE));
					break;
				case "sqale_rating":
					pme.setMainSqaleRating(this.uc.parseRatingToChar(jsonMetrics.getString(VALUE)));
					break;
				case "reliability_rating": // Reliability
					pme.setRelReliabilityRating(this.uc.parseRatingToChar(jsonMetrics.getString(VALUE)));
					break;
				case "bugs":
					pme.setRelBugs(jsonMetrics.getInt(VALUE));
					break;
				case "vulnerabilities": //Security
					pme.setSecVulnerabilities(jsonMetrics.getInt(VALUE));
					break;
				case "security_rating":
					pme.setSecSecurityRating(this.uc.parseRatingToChar(jsonMetrics.getString(VALUE)));
					break;
				case "alert_status":
					pme.setAlertStatus(jsonMetrics.getString(VALUE));
					break;
				default:
					LOG.info("Default case: " + jsonMetrics);
					break;
				}

			}

		} catch (JSONException e) {
			LOG.error("Error fiding all projects -> " + e.getMessage());
		}

		return pme;
	}

	public void syncrhonizeProject() {
		// This method is not implemented yet
	}

	/**
	 * Cabeceras para conectar con sonarQube
	 * 
	 * @return
	 */
	private static HttpHeaders getHeaders() {
		String plainCredentials = "admin:admin";
		String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Credentials);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		return headers;
	}
}
