package com.atos.coderank.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atos.coderank.components.JsonSerializers;
import com.atos.coderank.entities.GroupEntity;
import com.atos.coderank.entities.ProjectMetricsEntity;
import com.atos.coderank.entities.UserEntity;
import com.atos.coderank.services.ProjectMetricsService;
import com.atos.coderank.services.UserService;

@RestController
@RequestMapping("api/private/projectmetrics")
public class ProjectMetricsController {

	@Autowired
	@Qualifier("projectMetricsService")
	private ProjectMetricsService pms;
	
	@Autowired
	@Qualifier("userService")
	private UserService us;
	
	@Autowired
	@Qualifier("jsonSerializers")
	private JsonSerializers js;

	
	@GetMapping("")
	public ResponseEntity<List<ProjectMetricsEntity>> findAllProjectMetricsFromUser(HttpServletRequest req){
		String das = req.getParameter("das");
		//Find user groups		
		UserEntity user = this.us.findByDas(das);
		HttpStatus status = HttpStatus.NOT_FOUND;		
		List<ProjectMetricsEntity> list = new ArrayList<>();
		
		if(user != null) {
			status = HttpStatus.OK;			
			for (GroupEntity group : user.getGroups()) {
				List<ProjectMetricsEntity> pme = null;
				if(group.getProject() != null) {
					pme = this.pms.findAllByProjectId(group.getProject().getProjectId());
					for (int i = 0; i < pme.size(); i++) {
						pme.get(i).setSerializedProject(this.js.projectEntitySerializer(group));
					}
				}
				if(pme != null)
					list.addAll(pme);
			}
		}
		
		
		return new ResponseEntity<>(list, status);
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<ProjectMetricsEntity> findByProjectId(@PathVariable String projectId){
		ProjectMetricsEntity entity = this.pms.findMostRecentByProjectId(projectId);
		HttpStatus status = HttpStatus.OK;		
		
		return new ResponseEntity<>(entity, status);
	}
}
