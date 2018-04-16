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

import com.atos.coderank.entities.BadgesEntity;
import com.atos.coderank.entities.ProjectEntity;
import com.atos.coderank.entities.UserEntity;
import com.atos.coderank.services.BadgesService;
import com.atos.coderank.services.ProjectService;
import com.atos.coderank.services.UserService;

@RestController
@RequestMapping("api/private/badges")
public class BadgesController {

	@Autowired
	@Qualifier("badgesService")
	private BadgesService bs;
	
	@Autowired
	@Qualifier("userService")
	private UserService us;
	
	@Autowired
	@Qualifier("projectService")
	private ProjectService ps;

	@GetMapping("")
	public ResponseEntity<List<BadgesEntity>> findAll() {
		List<BadgesEntity> list = this.bs.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/{badgeId}")
	public ResponseEntity<BadgesEntity> findByIdOrName(@PathVariable String badgeId) {
		BadgesEntity entity = this.bs.findById(Long.parseLong(badgeId));
		HttpStatus status = HttpStatus.OK;

		if (null == entity)
			status = HttpStatus.NOT_FOUND;

		return new ResponseEntity<>(entity, status);
	}
	
	/**
	 * TODO I'm to sure if this function should go here.
	 * It recieves a Badge holding an array with (User/Project)Entity to relate them with this badge
	 * @param badge
	 * @return
	 */
	public ResponseEntity<BadgesEntity> assignBadgeToEntity(@RequestBody BadgesEntity badge){
		BadgesEntity entity = this.bs.findById(badge.getBadgeId());
		HttpStatus status = HttpStatus.OK;

		if (null == entity)
			status = HttpStatus.NOT_FOUND;
		else {
			
			//Add users to this badge			
			if(null != badge.getUsers())
				for (UserEntity user : badge.getUsers())
					entity.addUser(this.us.findByDas(user.getDas()));
			
			//Add projects to this badge
			if(null != badge.getProject())
				for (ProjectEntity project : badge.getProject()) {
					//FindProject
					project = this.ps.findById(project.getProjectId());
					entity.addProject(project);
				}
					
			
			entity = this.bs.saveOrUpdate(entity);
		}

		return new ResponseEntity<>(entity, status);
	}
	
}
