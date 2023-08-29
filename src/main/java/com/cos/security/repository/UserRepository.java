package com.cos.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.security.model.User;

// CRUD 함수를 JPARepository 가 들고 있음
// @Repository 라는 어노테이션이 없어도IoC가 됨. 이유는 JpaRepository 를 상속했기 때문
public interface UserRepository extends JpaRepository<User, Integer> {

}
