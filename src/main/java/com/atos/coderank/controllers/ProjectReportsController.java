package com.atos.coderank.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atos.coderank.components.JsonSerializers;
import com.atos.coderank.entities.ProjectReportsEntity;
import com.atos.coderank.services.ProjectReportsService;

@RestController
@RequestMapping("api/private/reports")
public class ProjectReportsController {

	private static final Log LOG = LogFactory.getLog(ProjectReportsController.class);
	
	@Autowired
	@Qualifier("projectReportsService")
	private ProjectReportsService prs;
	
	@Autowired
	@Qualifier("jsonSerializers")
	private JsonSerializers js;
	
	@GetMapping("")
	public ResponseEntity<List<ProjectReportsEntity>> findAll() {
		List<ProjectReportsEntity> list = this.prs.findAll();
			
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setSerializedProject(this.js.projectEntitySerializer(list.get(i).getProject()));
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/allbysqm")
	public ResponseEntity<List<ProjectReportsEntity>> findAllBySQMDas(HttpServletRequest req) {
		
		List<ProjectReportsEntity> list = this.prs.findAllBySQMDas(req.getParameter("das").toUpperCase());
		
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setSerializedProject(this.js.projectEntitySerializer(list.get(i).getProject()));
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProjectReportsEntity> findById(@PathVariable("id") String id){
		ProjectReportsEntity pre = this.prs.findById(Long.parseLong(id));

		HttpStatus status = HttpStatus.OK;

		if (pre == null)
			status = HttpStatus.NOT_FOUND;
		else 
			pre.setSerializedProject(this.js.projectEntitySerializer(pre.getProject()));
		
		return new ResponseEntity<>(pre, status);
	}
}
