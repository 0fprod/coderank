package com.atos.coderank.controllers;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atos.coderank.components.JsonSerializers;
import com.atos.coderank.entities.BadgesEntity;
import com.atos.coderank.entities.GroupEntity;
import com.atos.coderank.entities.UserEntity;
import com.atos.coderank.services.BadgesService;
import com.atos.coderank.services.GroupService;
import com.atos.coderank.services.UserService;


@RestController
@RequestMapping("api/private/users")
public class UserController {

	private static final Log LOG = LogFactory.getLog(UserController.class);

	@Autowired
	@Qualifier("userService")
	private UserService us;

	@Autowired
	@Qualifier("groupService")
	private GroupService gs;
	
	@Autowired
	@Qualifier("badgesService")
	private BadgesService bs;
	
	@Autowired
	@Qualifier("jsonSerializers")
	private JsonSerializers js;

	@GetMapping("/{das}")
	public ResponseEntity<UserEntity> findUserByDas(@PathVariable String das) {
		UserEntity user = this.us.findByDas(das);

		HttpStatus status = HttpStatus.OK;

		if (null == user)
			status = HttpStatus.NOT_FOUND;
		else {
			user.setSerializedGroups(this.js.groupEntitySerializer(user));
			user.setSerializedBadges(this.js.badgeListSerializer(user));
		}

		return new ResponseEntity<>(user, status);
	}

	@PostMapping("/save")
	public ResponseEntity<UserEntity> saveOrUpdate(@RequestBody UserEntity user) {
		LOG.info("Save or update " + user);
		UserEntity returnedUser = this.us.saveOrUpdate(user);
		HttpStatus status = HttpStatus.CREATED;

		if (null != returnedUser)
			status = HttpStatus.CONFLICT;

		return new ResponseEntity<>(returnedUser, status);
	}

	@GetMapping("")
	public ResponseEntity<List<UserEntity>> findAll() {
		List<UserEntity> users = this.us.findAll();

		for (int i = 0; i < users.size(); i++) {
			users.get(i).setSerializedGroups(this.js.groupEntitySerializer(users.get(i)));
			users.get(i).setSerializedBadges(this.js.badgeListSerializer(users.get(i)));
		}

		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteUser(@RequestBody UserEntity user) {
		UserEntity userToDelete = this.us.findByDas(user.getDas());
		String response = "The user with das " + user.getDas() + " was successfully deleted.";
		HttpStatus status = HttpStatus.OK;

		if (null == userToDelete) {
			status = HttpStatus.NOT_FOUND;
			response = "Cannot delete user with das '" + user.getDas() + "'. User doesnt exist.";
		} else {
			// Remove user from groups
			List<GroupEntity> userGroups = userToDelete.getGroups();
			for (GroupEntity group : userGroups) {
				group.removeUser(userToDelete);
				this.gs.saveOrUpdate(group);
			}
			// Remove user from badges
			List<BadgesEntity> userBadges = userToDelete.getBadges();
			for (BadgesEntity badge : userBadges) {
				badge.removeUser(userToDelete);
				this.bs.saveOrUpdate(badge);
			}
			this.us.deleteUser(userToDelete);
		}

		return new ResponseEntity<>(response, status);
	}


}
