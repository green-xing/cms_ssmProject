package com.briup.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cms_jd1911")
public class TestController {
	
	@GetMapping("/test1")
	public String show() {
		
		return "hello world";
		
	}
	
}
