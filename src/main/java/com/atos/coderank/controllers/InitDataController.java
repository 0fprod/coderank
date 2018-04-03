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
import com.atos.coderank.models.GroupModel;
import com.atos.coderank.models.RoleModel;
import com.atos.coderank.models.UserModel;
import com.atos.coderank.services.GroupService;
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

	@GetMapping("/gen-roles")
	public ResponseEntity<List<RoleModel>> createRoles() {
		List<RoleModel> list = new ArrayList<>();

		list.add(new RoleModel("ROLE_ADMIN"));
		list.add(new RoleModel("ROLE_SQM"));
		list.add(new RoleModel("ROLE_OM"));
		list.add(new RoleModel("ROLE_DEV"));

		for (RoleModel role : list) {
			this.rs.saveOrUpdate(role);
		}

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/sonar-groups")
	public ResponseEntity<List<GroupModel>> syncrhonizeGroupsFromSonar() {
		List<GroupModel> list = this.su.findAllGroups();
		List<GroupModel> saved = new ArrayList<>();

		for (GroupModel group : list) {
			saved.add(this.gs.saveOrUpdate(group));
		}

		return new ResponseEntity<>(saved, HttpStatus.OK);
	}

	@GetMapping("/sonar-users")
	public ResponseEntity<List<UserModel>> syncrhonizeUsersFromSonar() {
		List<UserModel> list = this.su.findAllUsers();
		List<UserModel> saved = new ArrayList<>();

		for (UserModel user : list) {
			// SetDefault role to Developer
			RoleModel role = this.rs.findByName("ROLE_DEV");
			user.setRole(role);			
			saved.add(this.us.saveOrUpdate(user));
			// Decirle al grupo que tiene un usuario
			for (GroupModel group : user.getGroups()) {
				GroupModel g = this.gs.findByName(group.getName());
				g.getUsers().add(user);
				this.gs.saveOrUpdate(g);
			}
		}

		return new ResponseEntity<>(saved, HttpStatus.OK);
	}
}
