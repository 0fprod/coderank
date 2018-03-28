package com.atos.coderank.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atos.coderank.models.ProjectModel;

@RestController
@RequestMapping("api/private/projects")
public class ProjectController {

	@GetMapping("")
	public ResponseEntity<List<ProjectModel>> findAll(){
		return null;
	}
	
	@GetMapping("/{project_id}")
	public ProjectModel findById(@PathVariable String project_id) {
		return null;
	}
	
	@PostMapping("/save")
	public ProjectModel saveOrUpdate(@RequestBody ProjectModel project) {
		return null;
		
	}
}
