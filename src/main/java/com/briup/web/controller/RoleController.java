package com.briup.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.bean.Role;
import com.briup.bean.extend.RoleExtend;
import com.briup.service.interfaces.IRole;
import com.briup.utils.Message;
import com.briup.utils.MessageUtil;

import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private IRole roleService;
	
	@ApiOperation(value="查询所有")
	@GetMapping(value="findAll")
	public Message findAll() {
		List<Role> list = roleService.findAll();
		return MessageUtil.success(list);
	}
	
	@ApiOperation(value="查询所有",notes="级联权限")
	@GetMapping(value="casecadePrivilegeFindAll")
	public Message casecadePrivilegeFindAll() {
		List<RoleExtend> list = roleService.casecadePrivilegeFindAll();
		return MessageUtil.success(list);
	}
	
	@ApiOperation(value="通过id删除")
	@GetMapping(value="deleteById")
	public Message deleteById(Integer id)
	{
		roleService.deleteById(id);
		return MessageUtil.success("删除成功");
	}
	
	@ApiOperation(value="保存或更新")
	@PostMapping(value="saveOrUpdate")
	public Message saveOrUpdate(Role role) {
		roleService.saveOrUpdate(role);
		return MessageUtil.success("更新成功");
	}
	
	@ApiOperation(value="为角色授权")
	@PostMapping(value="authorization")
	public Message authorization(Integer id,Integer[] privileges) {
		List<Integer> ids = Arrays.asList(privileges);
		roleService.authorization(id, ids);
		return MessageUtil.success("授权成功");
	}
	
}
