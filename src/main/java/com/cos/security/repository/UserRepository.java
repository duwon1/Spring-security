package com.cos.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.security.model.User;
import java.util.List;


// CRUD 함수를 JPARepository 가 들고 있음
// @Repository 라는 어노테이션이 없어도IoC가 됨. 이유는 JpaRepository 를 상속했기 때문
public interface UserRepository extends JpaRepository<User, Integer> {
	// findBy 규칙 -> Username 문법
	// select * from user where username = ?;
	public User findByUsername(String username); // JPA Query methods
	
	// select * from user where email = ?;
	// public User findByEmail();
}
