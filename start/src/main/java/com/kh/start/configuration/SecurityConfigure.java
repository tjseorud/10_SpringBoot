package com.kh.start.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigure {
//	필터체인 정의
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		//return httpSecurity.formLogin().disable().build();	
		/*
		return httpSecurity.formLogin(new Customizer<FormLoginConfigurer<HttpSecurity>>() {
			@Override
			public void customize(FormLoginConfigurer<HttpSecurity> t) {
				t.disable();
			}
		}).build();
		
		Cross Site Request Forgery
		
		<img src="http://도메인/logout" />
		<form action="http://도메인/logout" action="post">
		</form>
		*/
		return httpSecurity.formLogin(AbstractHttpConfigurer::disable)
							.httpBasic(AbstractHttpConfigurer::disable)
							.csrf(AbstractHttpConfigurer::disable)
							.authorizeHttpRequests(requests -> {
								requests.requestMatchers(HttpMethod.POST, "/auth/login", "/members").permitAll();
								requests.requestMatchers("/admin/**").hasRole("ADMIN");
								requests.requestMatchers(HttpMethod.PUT,"/members").authenticated();
							})
							.build();
	}
	
//	인증용
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
//	비번용
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
