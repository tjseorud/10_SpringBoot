package com.kh.start.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kh.start.auth.util.JwtUtil;
import com.kh.start.configuration.filter.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfigure {
	
	private final JwtFilter filter;
	
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
								requests.requestMatchers(HttpMethod.POST, "/auth/login", "/auth/refresh", "/members").permitAll();
								requests.requestMatchers("/admin/**").hasRole("ADMIN");
								requests.requestMatchers(HttpMethod.GET,"/uploads/**", "/boards/**", "/comments/**").permitAll();
								requests.requestMatchers(HttpMethod.PUT,"/members","/boards/**").authenticated();
								requests.requestMatchers(HttpMethod.DELETE, "/members","/boards/**").authenticated();
								requests.requestMatchers(HttpMethod.POST, "/boards","/comments").authenticated();
							})
							/*
							 * sessionManagement : 세션을 어떻게 관리할것인지 지정할수 있음
							 * sessionCreationPolicy : 세션 사용 정책을 설정			
							 */
							.sessionManagement(manager ->
								manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
							)
							.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
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
