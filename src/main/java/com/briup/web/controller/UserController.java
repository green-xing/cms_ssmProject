package com.briup.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.bean.extend.UserExtend;
import com.briup.service.interfaces.IUser;
import com.briup.utils.Message;
import com.briup.utils.MessageUtil;
import com.briup.vm.UserVM;

import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUser userExtend;
	
	@PostMapping("login")
	public Message login(@RequestBody UserVM uservm) {
		
		Map<String,String> map = new HashMap<>();
		map.put("token", "admin-token");
		return MessageUtil.success(map);
	}
	
	@ApiOperation(value="通过token获取用户的基本信息")
	@GetMapping("info")
	public Message info(String token) {
		
		//1、通过token获取客户信息
		UserExtend userex = userExtend.findById(1);
		return MessageUtil.success(userex);
		
	}
	@PostMapping("logout")
	public Message logout() {
		return MessageUtil.success("退出成功");
	}
	
}
