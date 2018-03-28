package com.atos.coderank.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;


public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

	private static final Log LOG = LogFactory.getLog(JwtAuthenticationTokenFilter.class);
	
	protected JwtAuthenticationTokenFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
		LOG.info("JWTAthTokFilter constructor");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		LOG.info("Attemp");
		String header = req.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {			
			res.sendError(400, "JWT Token is missing");
		}

		String authenticationToken = header.substring(7);

		JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
		return getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		LOG.info("success");
		chain.doFilter(request, response);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {		
		LOG.info("unsuccessful");
		response.sendError(401, "Wrong credentials");
	}

}
