## Mysql DB 및 사용자 생성 
```sql
create user 'cos'@'%' identified by '1234';
grant all privileges on *.* to 'cos'@'%';
create database security;
use security;
```
## 사용자 권한 설정 
```java 
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
	
	@Bean
	BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();  
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception   {
		http.csrf(AbstractHttpConfigurer::disable);
		http.authorizeHttpRequests(authorize -> {
			authorize
				.requestMatchers("/user/**").authenticated() // 인증만 되면 허용됨
				.requestMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER") // manager, admin 권한 둘중 하나가 있어야 허용
				// 데이터베이스에 데이터를 저장할때 ROLE_XXXX 라고 반드시 저장해야 인식을 함
				.requestMatchers("/admin/**").hasRole("ADMIN") // admin 권한이 있어야 허용
			    	.anyRequest().permitAll(); // 위에 해당할 경우 무조껀 허용
			}
		).formLogin(formLogin -> formLogin
			.loginPage("/loginForm") // 로그인 From url 경로
			.loginProcessingUrl("/login") // 로그인 Action url 경로
			.defaultSuccessUrl("/") // 로그인 성공했을떄 기본 url 경로
		);		
		return http.build();
	}

}

```
