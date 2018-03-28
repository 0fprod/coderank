package com.atos.coderank.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final Log LOG = LogFactory.getLog(JwtAuthenticationEntryPoint.class);
	
	@Override
	public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException ex) throws IOException, ServletException {		
		LOG.info("commence " + req);
		//res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
	
	}

}
