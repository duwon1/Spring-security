package com.cos.security.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

// @Entity가 붙은 클래스는 JPA가 관리하는 것으로, 엔티티라고 불림
@Entity
@Data
public class User {
	@Id // primary key (기본키)
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 1씩 증가하는 기본키를 increment 를 해줌
	private int id;
	private String username;
	private String password;
	private String email;
	private String role;
	@CreationTimestamp // insert 가 생길때마다 현재시간을 채워넣음
	private Timestamp createDate;
	@UpdateTimestamp // update 가 발생할때 마다 현재시간을 채워넣음
	private Timestamp updateDate;
	

}
