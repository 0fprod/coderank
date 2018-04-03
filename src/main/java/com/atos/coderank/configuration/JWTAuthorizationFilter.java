package com.atos.coderank.configuration;

import static com.atos.coderank.configuration.SecurityConstants.HEADER_STRING;
import static com.atos.coderank.configuration.SecurityConstants.SECRET;
import static com.atos.coderank.configuration.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {

		String header = req.getHeader(HEADER_STRING);
		
		if(header == null || !header.startsWith(TOKEN_PREFIX)) {
			filterChain.doFilter(req, res);
		}
		
		UsernamePasswordAuthenticationToken utap = getAuthentication(req);
		
		SecurityContextHolder.getContext().setAuthentication(utap);
		filterChain.doFilter(req, res);		
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {

		String token = req.getHeader(HEADER_STRING);
		if(token != null) {
			//parse the token
			String user = Jwts.parser()
							.setSigningKey(SECRET.getBytes())
							.parseClaimsJws(token.replaceAll(TOKEN_PREFIX, ""))
							.getBody()
							.getSubject();
			if(user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
			
		}
		return null;
	}
	
	
}
