package com.cos.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.security.model.User;
import com.cos.security.repository.UserRepository;

@Controller
public class IndexController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	// localhost:8070/
	@GetMapping("/")
	public String index() {
		// mustache 기본폴터 scr/main/resources/
		// view 리졸브 설정 : templates(prefix), .mustache(suffix) 생략가능
		return "index";
	}
	
	@GetMapping("/user")
	public @ResponseBody String user() {
		return "user";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	@GetMapping("/manager")
	public String manager() {
		return "manager";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
	@GetMapping("/login")
	public String login() {
		return "loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	@PostMapping("/join")
	public String join(User user) {
		System.out.println(user);
		user.setRole("user");
		String rawPassword = user.getPassword(); // 전달받은 비밀번호를 변수에 담음
		String encPassword = bCryptPasswordEncoder.encode(rawPassword); // 비밀번호를 bCrypt 를 이용한 암호화를 진행후에 변수에 저장
		user.setPassword(encPassword); // 암호화 된 비밀번호를 user 객체에 저장
		userRepository.save(user); // insert
			
		return "redirect:/loginForm";
	}
	
}
