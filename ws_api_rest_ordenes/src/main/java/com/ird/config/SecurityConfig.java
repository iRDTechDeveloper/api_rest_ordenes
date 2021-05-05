package com.ird.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ird.config.security.RestAuthenticationEntryPoiny;
import com.ird.config.security.TokenAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public TokenAuthenticationFilter createTokenAuthenticationFilter() {
		return new TokenAuthenticationFilter();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.csrf()
				.disable()
			.formLogin()
				.disable()
			.httpBasic()
				.disable()
			.exceptionHandling()
				.authenticationEntryPoint(new RestAuthenticationEntryPoiny())
				.and()
			.authorizeRequests()
				.antMatchers(
						"/crearUsuario",
						"/login"
						)
					.permitAll()
				.anyRequest()
					.authenticated()
					;
		
		http.addFilterBefore(createTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
