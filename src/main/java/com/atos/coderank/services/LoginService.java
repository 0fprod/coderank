package com.atos.coderank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atos.coderank.repositories.UserRepository;

@Service("loginService")
public class LoginService {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository ur;	
	
	public String findByDasAndPassword(String das, String password) {	

		return "";
	}

}
