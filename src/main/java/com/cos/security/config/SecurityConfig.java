package com.cos.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// @Configuration 어노테이션
// 설정파일을 만들기위한 어노테이션 or Bean을 등록하기위한 어노테이션 이라고 생각하면 된다.
// 단순한 어노테이션이 아닌 Bean 을 등록할때 싱글톤이 될 수 있도록 보장해줌
// 스프링컨테이너에서 Bean을 직접 관리할 수 있게됨
@Configuration
@EnableWebSecurity
public class SecurityConfig {
//	authenticated() ;  인증된 사용자의 접근을 허용
//	fullyAuthenticated(): 인증된 사용자의 접근을 허용,  rememberMe인증 제외
//	permitAll(): 무조건 허용
//	denyAll(): 무조건 차단
//	anonymous(): 익명사용자 허용
//	rememberMe(): rememberMe 인증 사용자 접근 허용
//	access(String): 주어진 SpEL표현식의 평가 결과가 true 이면 접근허용
//	hasRole(String): 사용자가 주어진 역할이 있다면 접근을 허용
//	hasAuthority(String): 사용자가 주어진 권한이 있다면 허용
//	hasAnyRole(String...): 사용자가 주어진 어떤권한이라도 있으면 허용
//	hasAnyAuthority(String...): 사용자가 주어진 권한중 어떤 것이라도 있다면 허용
//	hasIpAddress(String): 주어진 IP로 부터 요청이 왔다면 접근을 허용
	
	// 해당 메서드의 리턴되는 오브젝트를 loC로 등록해줌
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();  
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception   {
		http.csrf(AbstractHttpConfigurer::disable);
		http.authorizeHttpRequests(authorize -> {
			try {
				authorize
					.requestMatchers("/user/**").authenticated() // 인증만 되면 허용됨
					.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN") // manager, admin 권한 둘중 하나가 있어야 허용
				    .requestMatchers("/admin/**").hasRole("ADMIN") // admin 권한이 있어야 허용
				    .anyRequest().permitAll()
				    .and().formLogin().loginPage("/loginForm") // 해당 권한이 없을시 /login 페이지로 리다이렉트
				    .loginProcessingUrl("/login") // /login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해줌
				    .defaultSuccessUrl("/"); // 로그인 성공시 이동 할 페이지
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	        
		);
		
		return http.build();
	}

}
