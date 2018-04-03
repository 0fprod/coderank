package com.atos.coderank.controllers;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import com.atos.coderank.models.UserModel;
import com.atos.coderank.services.UserService;

@RestController
@RequestMapping("api/private/users")
public class UserController {

	private static final Log LOG = LogFactory.getLog(UserController.class);

	@Autowired
	@Qualifier("userService")
	private UserService us;

	@GetMapping("/{das}")
	public ResponseEntity<UserModel> findUserByDas(@PathVariable String das) {
		UserModel user = this.us.findByDas(das);
		HttpStatus status = HttpStatus.OK;

		if (null == user)
			status = HttpStatus.NOT_FOUND;

		return new ResponseEntity<>(user, status);
	}

	@PostMapping("/save")
	public ResponseEntity<UserModel> saveOrUpdate(@RequestBody UserModel user) {
		LOG.info("Save or update " + user);
		UserModel returnedUser = this.us.saveOrUpdate(user);

		LOG.info("ReturnedUser " + returnedUser);
		HttpStatus status = HttpStatus.OK;

		return new ResponseEntity<>(user, status);
	}

	@GetMapping("")
	public ResponseEntity<List<UserModel>> findAll() {
		List<UserModel> users = this.us.findAll();

		return new ResponseEntity<>(users, HttpStatus.OK);
	}
}
