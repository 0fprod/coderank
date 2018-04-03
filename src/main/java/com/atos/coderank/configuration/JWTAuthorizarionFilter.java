package com.atos.coderank.configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizarionFilter extends BasicAuthenticationFilter{

	public JWTAuthorizarionFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
//https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/?utm_source=medium&utm_medium=sc&utm_campaign=spring_boot_api
}
