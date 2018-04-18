package com.atos.coderank.configuration;

/**
 * Following: https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/?utm_source=medium&utm_medium=sc&utm_campaign=spring_boot_api
 * @author A679647
 *
 */
public class SecurityConstants {
	public static final String SECRET = "CpdeRank";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String LOGIN_URL = "/api/auth";
    
    private SecurityConstants() {
    	
    }
}
