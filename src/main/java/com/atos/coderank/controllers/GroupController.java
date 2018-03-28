package com.atos.coderank.controllers;

import java.util.ArrayList;
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

import com.atos.coderank.components.SonarUtils;
import com.atos.coderank.models.GroupModel;
import com.atos.coderank.services.GroupService;

@RestController
@RequestMapping("api/private/groups")
public class GroupController {

	@Autowired
	@Qualifier("groupService")
	private GroupService gs;
	
	@Autowired
	@Qualifier("sonarUtils")
	private SonarUtils su;
	
	@GetMapping("")
	public ResponseEntity<List<GroupModel>> findAll(){
		List<GroupModel> list = this.gs.findAll();		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/{group_id}")
	public ResponseEntity<GroupModel> findByGroupId(@PathVariable String group_id){
		Long id = Long.parseLong(group_id);
		GroupModel group = this.gs.findByGroupId(id);
		HttpStatus status = HttpStatus.OK;
		
		if(null == group)
			status = HttpStatus.NOT_FOUND;
				
		return new ResponseEntity<>(group, status);
	}
	
	@PostMapping("/save")
	public ResponseEntity<GroupModel> saveOrUpdate(@RequestBody GroupModel group){
		GroupModel gm = this.gs.saveOrUpdate(group);
		
		return new ResponseEntity<>(gm, HttpStatus.OK);
	}

	@GetMapping("/sonar-groups")
	public ResponseEntity<List<GroupModel>> syncrhonizeGroupsFromSonar(){
		List<GroupModel> list = this.su.findAllGroups();
		List<GroupModel> saved = new ArrayList<>();
		
		for(GroupModel group : list) {
			saved.add(this.gs.saveOrUpdate(group));
		}
		
		return new ResponseEntity<>(saved, HttpStatus.OK);
	}
}
