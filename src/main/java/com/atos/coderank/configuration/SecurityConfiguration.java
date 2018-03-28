package com.atos.coderank.configuration;

import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final Log LOG = LogFactory.getLog(SecurityConfiguration.class);
	
	@Autowired
	@Qualifier("userService")
	private UserDetailsService us;

	@Autowired
	private JwtAuthenticationProvider authenticationProvider;

	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		LOG.info("configure Global");
		auth.userDetailsService(us).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilter() {
		LOG.info("authTokenFilter");
		AuthenticationManager am = new ProviderManager(Collections.singletonList(authenticationProvider));
		JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter("api/private/**");
		filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
		filter.setAuthenticationManager(am);

		return filter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.cors()
		.and()
			.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests().antMatchers("/api/auth","/api/public/**").permitAll()
		.and()
			.authorizeRequests().antMatchers("/api/private/**").authenticated()
		.and()
			.exceptionHandling().authenticationEntryPoint(entryPoint)
		.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.headers()
			.cacheControl();
	}

}
