package com.cos.security.config.auth;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.security.model.User;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행합니다
// 로그인을 진행이 완료가 되면 시큐리티 session을 만들어줌 (Security ContextHolder)
// 오브젝트 => Authentication 타입 객체
// Authentication 안에 User 정보가 있어야 함
// User 오브젝트 타입 => UserDetails 타입 객체

// Security Session => Authentication => UserDetails => User

public class PrincipalDetails implements UserDetails {

	private User user; // 컴포지션

	public PrincipalDetails(User user) {
		this.user = user;
	}

	// 해당 User 의 권한을 리턴하는 곳
	// 리턴타입이 Collection 타입이기때문에 String 형인 user.getRole() 을 바로 리턴할 수 없음
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		return collect;
	}

	// 비밀번호를 리턴하는 메서드
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	// username을 리턴하는 메서드
	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정의 잠긴 여부를 리턴함( true 라면 만료되지 않음을 의미 )
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정의 잠긴 여부를 리턴함 ( true 라면 계정이 잠겨있지 않음을 의미 )
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 계정의 만료 여부를 리턴함 ( true 라면 만료되지 않은 계정을 의미 )
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//  계정의 사용가능한 여부를 리턴함 ( true 라면 사용가능한 계정을 의미 )
	@Override
	public boolean isEnabled() {
		
		// 마지막 로그인을 하고 1시간이 지나면 계정이 만료되도록 설정
		
		
		return true;
	}

}
