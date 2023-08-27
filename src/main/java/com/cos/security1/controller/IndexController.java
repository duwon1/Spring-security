package com.cos.security1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	// localhost:8080/
	@GetMapping("/")
	public String index() {
		// 머스테치 기본폴터 scr/main/resources/
		// view 리졸브 설정 : templates(prefix), .mustache(suffix) 생략가능
		return "index";
	}
	
}
