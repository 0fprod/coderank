package com.atos.coderank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atos.coderank.entities.ProjectEntity;
import com.atos.coderank.services.ProjectService;

@RestController
@RequestMapping("api/private/projects")
public class ProjectController {

	@Autowired
	@Qualifier("projectService")
	private ProjectService ps;

	@GetMapping("")
	public ResponseEntity<List<ProjectEntity>> findAll() {
		List<ProjectEntity> list = this.ps.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/{project_id}")
	public ResponseEntity<ProjectEntity> findById(@PathVariable String project_id) {
		ProjectEntity project = this.ps.findById(project_id);
		HttpStatus status = HttpStatus.OK;
		if (project == null)
			status = HttpStatus.NOT_FOUND;

		return new ResponseEntity<>(project, status);
	}

	@PostMapping("/save")
	public ProjectEntity saveOrUpdate(@RequestBody ProjectEntity project) {
		return null;

	}
}
