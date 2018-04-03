package com.atos.coderank.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atos.coderank.models.UserModel;
import com.atos.coderank.services.LoginService;

@RestController
@RequestMapping("api/auth")
public class LoginController {

	private final static Log LOG = LogFactory.getLog(LoginController.class);
	
	@Autowired
	@Qualifier("loginService")
	private LoginService ls;
		
	@PostMapping("")
	public ResponseEntity<String> login(@RequestBody UserModel user){
		String token = this.ls.findByDasAndPassword(user.getDas(), user.getPassword());
		
		return new ResponseEntity<>(token, HttpStatus.OK);
	}
}
