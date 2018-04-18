package com.atos.coderank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atos.coderank.components.SonarUtils;
import com.atos.coderank.entities.ProjectEntity;
import com.atos.coderank.entities.ProjectMetricsEntity;
import com.atos.coderank.services.ProjectService;

@RestController
@RequestMapping("api/private/projectmetrics")
public class ProjectMetricsController {

	@Autowired
	@Qualifier("sonarUtils")
	private SonarUtils us;
	
	@Autowired
	@Qualifier("projectService")
	private ProjectService ps;
	
	
	@GetMapping("test")
	public ResponseEntity<String> createMetrics() {
		ProjectEntity project = this.ps.findById("AWLT7DnUiEetjgKPc4Om");
		
		
		ProjectMetricsEntity pme = this.us.scanOneProject(project.getKey());
		pme.setProject(project);
		
		System.out.println("ahora");
		
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

}
