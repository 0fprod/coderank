package com.atos.coderank.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.atos.coderank.entities.GroupEntity;
import com.atos.coderank.entities.ProjectEntity;
import com.atos.coderank.entities.UserEntity;

/**
 * 
 * @author A679647 http://192.168.99.100:32768/web_api/
 */
@Component("sonarUtils")
public class SonarUtils {

	private static final String SONAR_API = "http://192.168.99.100:32768/api/";
	private static final String SONAR_URL = "http://192.168.99.100:32768/dashboard";

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
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * GET /user_groups/search?q={group-name}
	 */
	public void findOneGroup() {
	}

	public void createGroup() {
	}

	public void deleteGroup() {
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
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * GET users/groups?login={das}
	 */
	public void findOneUser() {
	}

	public void createUser() {
	}

	public void deleteUser() {
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
			
			for(int i = 0; i < jsonProjects.length(); i++) {
				JSONObject jsonProject = jsonProjects.getJSONObject(i);
				ProjectEntity project = new ProjectEntity();
				
				project.setProjectId(jsonProject.getString("id"));	
				project.setName(jsonProject.getString("name"));
				project.setKey(jsonProject.getString("key"));
				project.setUrl(SONAR_URL + "?id=" + project.getKey());
				list.add(project);
			}
		

		} catch (JSONException e) {
			e.printStackTrace();
		}	
		return list;
	}

	/**
	 * ProjectKey convention -> groupname:projectname
	 * GET /projects/search?projects={project-key}
	 */
	public ProjectEntity findOneProject(String key) {
		return null;
	}

	public void createProject() {
	}

	public void deleteProject() {
	}

	/**
	 * GET
	 * api/measures/component?component={project-key}&metricKeys={nclock,complexity,violations..etc}
	 */
	public void scanOneProject() {
	}

	public void syncrhonizeProject() {
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
