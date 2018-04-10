package com.atos.coderank.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.atos.coderank.entities.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static com.atos.coderank.configuration.SecurityConstants.EXPIRATION_TIME;
import static com.atos.coderank.configuration.SecurityConstants.HEADER_STRING;
import static com.atos.coderank.configuration.SecurityConstants.SECRET;
import static com.atos.coderank.configuration.SecurityConstants.TOKEN_PREFIX;

/**
 * Following: https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/?utm_source=medium&utm_medium=sc&utm_campaign=spring_boot_api
 * @author A679647
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter (AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

		try {
			UserEntity creds = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
			UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(creds.getDas(), creds.getPassword(), new ArrayList<>());
			Authentication auth = this.authenticationManager.authenticate(upat);
			return auth;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
	
		User usrDetail = (User) authResult.getPrincipal();
		Date todayPlusExpirationTime = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
		String token = Jwts.builder()
				.setSubject(usrDetail.getUsername())
				.setExpiration(todayPlusExpirationTime)
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
				.compact();
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}
	
	
}
