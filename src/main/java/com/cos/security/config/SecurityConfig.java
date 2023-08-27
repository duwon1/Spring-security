package com.cos.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

// @Configuration 어노테이션
// 설정파일을 만들기위한 어노테이션 or Bean을 등록하기위한 어노테이션 이라고 생각하면 된다.
// 단순한 어노테이션이 아닌 Bean 을 등록할때 싱글톤이 될 수 있도록 보장해줌
// 스프링컨테이너에서 Bean을 직접 관리할 수 있게됨
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable);
		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/user/**").authenticated()
				.requestMatchers("/manager/**").access(new WebExpressionAuthorizationManager("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')"))
				// ROLE_ADMIN or ROLE_MANAGER 권한이 있는 사람만 manager 페이지에 접근이 가능
				.requestMatchers("/admin/**").access(new WebExpressionAuthorizationManager("hasRole('ROLE_ADMIN')"))
				// ROLE_ADMIN 권한이 있는 사람만 admin 페이지에 접근이 가능
				.anyRequest().denyAll()
			);
		
		return http.build();
	}

}
