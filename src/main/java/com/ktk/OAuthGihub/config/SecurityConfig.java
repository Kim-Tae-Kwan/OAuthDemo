package com.ktk.OAuthGihub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@EnableWebSecurity
public class SecurityConfig{
	
	@Bean
	public SecurityFilterChain securityFilterChainFormLogin(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeHttpRequests(a -> a
					.antMatchers("/", "/error", "/webjars/**").permitAll()
					.anyRequest().authenticated()
			)
			.exceptionHandling(e -> e
					// 바로 github redirect 하지 않기 위해
					.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
			)
			.logout(l -> l
					.logoutSuccessUrl("/").permitAll()
			)
			.oauth2Login();
			
		return http.build();
	}
}
