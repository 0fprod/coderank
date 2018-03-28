package com.atos.coderank.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.atos.coderank.entities.UserEntity;
import com.atos.coderank.models.UserModel;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private static final Log LOG = LogFactory.getLog(JwtAuthenticationProvider.class);
	
	@Autowired
	private JwtValidator validator;

	@Override
	protected void additionalAuthenticationChecks(UserDetails arg0, UsernamePasswordAuthenticationToken arg1)
			throws AuthenticationException {
		LOG.info("JwtAuthProvider - additionalAuthCheck");
	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
		LOG.info("RetriveUser");
		JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;
		String token = jwtAuthenticationToken.getToken();

		UserEntity user = validator.validate(token);
		UserModel userm = new UserModel();
		userm.setToken(token);
		userm.setDas(user.getDas());
		return userm;

	}

	@Override
	public boolean supports(Class<?> aClass) {
		return (JwtAuthenticationToken.class.isAssignableFrom(aClass));
	}

}
