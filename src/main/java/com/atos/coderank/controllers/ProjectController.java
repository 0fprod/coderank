package com.atos.coderank.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atos.coderank.entities.GroupEntity;
import com.atos.coderank.entities.ProjectEntity;
import com.atos.coderank.entities.UserEntity;
import com.atos.coderank.services.GroupService;
import com.atos.coderank.services.ProjectService;

@RestController
@RequestMapping("api/private/projects")
public class ProjectController {

	@Autowired
	@Qualifier("projectService")
	private ProjectService ps;

	@Autowired
	@Qualifier("groupService")
	private GroupService gs;

	@GetMapping("")
	public ResponseEntity<List<ProjectEntity>> findAll() {
		List<ProjectEntity> list = this.ps.findAll();

		for (int i = 0; i < list.size(); i++)
			list.get(i).setSerializedGroup(groupEntitySerializer(list.get(i)));

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/{project_id}")
	public ResponseEntity<ProjectEntity> findById(@PathVariable String project_id) {
		ProjectEntity project = this.ps.findById(project_id);
		HttpStatus status = HttpStatus.OK;
		
		if (project == null)
			status = HttpStatus.NOT_FOUND;
		else
			project.setSerializedGroup(groupEntitySerializer(project));

		return new ResponseEntity<>(project, status);
	}

	@PostMapping("/save")
	public ResponseEntity<ProjectEntity> saveOrUpdate(@RequestBody ProjectEntity project) {
		return null;
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(@RequestBody ProjectEntity project) {
		String response = "The project with id '" + project.getProjectId() + "' has been deleted.";
		HttpStatus status = HttpStatus.OK;
		ProjectEntity projectToDelete = this.ps.findById(project.getProjectId());

		if (null == projectToDelete) {
			response = "Cannot delete the project with id '" + project.getProjectId() + "'. It doesnt exist.";
			status = HttpStatus.NOT_FOUND;
		} else {
			GroupEntity group = projectToDelete.getGroup();
			group.setProject(null);
			this.gs.saveOrUpdate(group);
			this.ps.delete(projectToDelete);
		}

		return new ResponseEntity<>(response, status);
	}

	/**
	 * Serializes a ProjectEntity
	 * @param project
	 * @return
	 */
	private Map<String, String> groupEntitySerializer(ProjectEntity project) {
		Map<String, String> map = new HashMap<>();

		if (project.getGroup() != null) {
			GroupEntity group = project.getGroup();
			map.put("id", group.getGroupId().toString());
			map.put("name", group.getName());
			map.put("description", group.getDescription());
		}

		return map;
	}
}
