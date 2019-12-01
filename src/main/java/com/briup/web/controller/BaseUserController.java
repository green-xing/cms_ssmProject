package com.briup.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.bean.User;
import com.briup.bean.extend.UserExtend;
import com.briup.service.interfaces.IUser;
import com.briup.utils.Message;
import com.briup.utils.MessageUtil;
import com.briup.vm.UserRoleVM;

import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/baseUser")
public class BaseUserController {//用户基础控制类

	@Autowired
	private IUser userService;
	
	@ApiOperation(value="查询所有")
	@GetMapping(value="findAll")
	public Message findAll() {
		
		List<User> list = userService.findAll();
		
		return MessageUtil.success(list);
	}
	
	@ApiOperation(value="查询所有",notes="级联用户角色")
	@GetMapping(value="cascadeRoleFindAll")
	public Message cascadeRoleFindAll() {
		List<UserExtend> list = userService.cascadeRoleFindAll();
		return MessageUtil.success(list);
	}
	
	@ApiOperation(value="保存或者更新")
	@PostMapping(value="saveOrUpdate")
	public Message saveOrUpdate(User user) {
		userService.saveOrUpdate(user);
		return MessageUtil.success("更新成功");
	}
	
	@ApiOperation(value="通过id删除")
	@GetMapping(value="deleteById")
	public Message deleteById(Integer id) {
		userService.deleteById(id);
		return MessageUtil.success("删除成功");
	}
	
	@ApiOperation(value="设置权限")
	@PostMapping(value="setRoles")
	public Message setRoles(UserRoleVM userRoleVM) {
		userService.setRoles(userRoleVM.getId(), userRoleVM.getRoles());
		return MessageUtil.success("设置成功");
	}
	
	
}
