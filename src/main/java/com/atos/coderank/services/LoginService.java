package com.atos.coderank.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.atos.coderank.entities.UserEntity;
import com.atos.coderank.repositories.UserRepository;

@Service("loginService")
public class LoginService implements UserDetailsService {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository ur;

	@Override
	public UserDetails loadUserByUsername(String das) throws UsernameNotFoundException {
		UserEntity user = this.ur.findByDasIgnoreCase(das);
		
		if(user == null)
			throw new UsernameNotFoundException(das);
				
		return new User(user.getDas(), user.getPassword(), new ArrayList<>());
	}	
	


}
