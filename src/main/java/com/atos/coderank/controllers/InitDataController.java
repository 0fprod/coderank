package com.atos.coderank.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atos.coderank.components.SonarUtils;
import com.atos.coderank.entities.GroupEntity;
import com.atos.coderank.entities.ProjectEntity;
import com.atos.coderank.entities.RoleEntity;
import com.atos.coderank.entities.UserEntity;
import com.atos.coderank.services.GroupService;
import com.atos.coderank.services.ProjectService;
import com.atos.coderank.services.RoleService;
import com.atos.coderank.services.UserService;

@RestController
@RequestMapping("/api/private/initialize")
public class InitDataController {

	@Autowired
	@Qualifier("sonarUtils")
	private SonarUtils su;

	@Autowired
	@Qualifier("groupService")
	private GroupService gs;

	@Autowired
	@Qualifier("userService")
	private UserService us;

	@Autowired
	@Qualifier("roleService")
	private RoleService rs;

	@Autowired
	@Qualifier("projectService")
	private ProjectService ps;

	@GetMapping("/gen-roles")
	public ResponseEntity<List<RoleEntity>> createRoles() {
		List<RoleEntity> list = new ArrayList<>();

		list.add(new RoleEntity("ROLE_ADMIN"));
		list.add(new RoleEntity("ROLE_SQM"));
		list.add(new RoleEntity("ROLE_OM"));
		list.add(new RoleEntity("ROLE_DEV"));

		for (RoleEntity role : list) {
			this.rs.saveOrUpdate(role);
		}

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/sonar-groups")
	public ResponseEntity<List<GroupEntity>> syncrhonizeGroupsFromSonar() {
		List<GroupEntity> list = this.su.findAllGroups();
		List<GroupEntity> saved = new ArrayList<>();

		for (GroupEntity group : list) {
			saved.add(this.gs.saveOrUpdate(group));
		}

		return new ResponseEntity<>(saved, HttpStatus.OK);
	}

	@GetMapping("/sonar-users")
	public ResponseEntity<List<UserEntity>> syncrhonizeUsersFromSonar() {
		List<UserEntity> list = this.su.findAllUsers();
		List<UserEntity> saved = new ArrayList<>();

		for (UserEntity user : list) {
			saved.add(this.us.saveOrUpdate(user));
			// Decirle al grupo que tiene un usuario
			for (GroupEntity group : user.getGroups()) {
				GroupEntity g = this.gs.findByName(group.getName());
				g.getUsers().add(user);
				this.gs.saveOrUpdate(g);
			}
		}

		return new ResponseEntity<>(saved, HttpStatus.OK);
	}

	@GetMapping("sonar-projects")
	public ResponseEntity<List<ProjectEntity>> syncrhonizeProjectsFromSonar() {
		List<ProjectEntity> projects = this.su.findAllProjects();
		List<ProjectEntity> saved = new ArrayList<>();

		for (ProjectEntity project : projects) {
			ProjectEntity model = this.ps.saveOrUpdate(project);
			// Asignarle el grupo
			String groupname = model.getKey().substring(0, model.getKey().indexOf(':'));
			GroupEntity gm = this.gs.findByName(groupname);
			if(null != gm) {
				gm.setProject(model);
				this.gs.saveOrUpdate(gm);				
			}
			saved.add(model);
		}

		return new ResponseEntity<>(saved, HttpStatus.OK);
	}
}
