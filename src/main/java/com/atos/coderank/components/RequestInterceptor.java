package com.atos.coderank.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component("requestInterceptor")
public class RequestInterceptor extends HandlerInterceptorAdapter {

	private static final Log LOG = LogFactory.getLog(RequestInterceptor.class);

	/**
	 * Se ejecuta antes de entrar en los controller
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		LOG.info(" -- PreHandle request");

		if (request.getHeader("Authorization") == null) {
			LOG.error("Authorization token not found.");
			return false;
		}

		LOG.info("Endpoint --> " + request.getRequestURL().toString());
		return true;
	}

	/**
	 * Se ejecuta antes del return de los controller
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		LOG.info(" -- afterCompletion request");		

	}

}
