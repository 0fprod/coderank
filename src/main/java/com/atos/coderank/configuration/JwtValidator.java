package com.atos.coderank.configuration;


import org.springframework.stereotype.Component;

import com.atos.coderank.entities.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {

	private final String SECRET = "eqalizer";
	
    public UserEntity validate(String token) throws ExpiredJwtException {
    	UserEntity user = new UserEntity();
        Claims body = Jwts.parser()
     		   .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
             
        user.setDas(body.getSubject());
        return user;
        

     }
	
}
