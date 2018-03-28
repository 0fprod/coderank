package com.atos.coderank.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atos.coderank.models.UserModel;

@RestController
@RequestMapping("api/auth")
public class LoginController {

	@PostMapping("")
	public ResponseEntity<UserModel> login(@RequestBody UserModel user){
		
		return null;
	}
}
