package com.atos.coderank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atos.coderank.entities.ProjectMetricsEntity;
import com.atos.coderank.services.ProjectMetricsService;

@RestController
@RequestMapping("api/private/projectmetrics")
public class ProjectMetricsController {

	@Autowired
	@Qualifier("projectMetricsService")
	private ProjectMetricsService pms;
	
	@GetMapping("")
	public ResponseEntity<List<ProjectMetricsEntity>> findAll(){
		List<ProjectMetricsEntity> list = this.pms.findAllMostRecent();
		HttpStatus status = HttpStatus.OK;		
		
		return new ResponseEntity<>(list, status);
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<ProjectMetricsEntity> findByProjectId(@PathVariable String projectId){
		ProjectMetricsEntity entity = this.pms.findMostRecentByProjectId(projectId);
		HttpStatus status = HttpStatus.OK;		
		
		return new ResponseEntity<>(entity, status);
	}
}
