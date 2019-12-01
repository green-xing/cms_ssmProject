package com.briup.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.bean.Privilege;
import com.briup.service.interfaces.IPrivilege;
import com.briup.utils.Message;
import com.briup.utils.MessageUtil;
import com.briup.vm.PrivilegeTree;

import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/privilege")
public class PrivilegeController {
	
	@Autowired
	private IPrivilege privilege;
	
	@ApiOperation(value="查询所有")
	@GetMapping(value="findAll")
	public Message findAll() {
		List<Privilege> list = privilege.findAll();
		
		return MessageUtil.success(list);
	}
	
	@ApiOperation(value="通过parentId查询")
	@GetMapping(value="findByParentId")
	public Message findByParentId(Integer id) {
		List<Privilege> list = privilege.findByParentId(id);
		return MessageUtil.success(list);
	}
	
	@ApiOperation(value="保存或者更新")
	@PostMapping(value="saveOrUpdate")
	public Message saveOrUpdate(Privilege priv) {
		privilege.saveOrUpdate(priv);
		return MessageUtil.success("更新成功");
				
	}
	
	@ApiOperation(value="查询树")
	@GetMapping(value="findPrivilegeTree")
	public Message findPrivilegeTree() {
		List<PrivilegeTree> list = privilege.findPrivilegeTree();
		
		return MessageUtil.success(list);
	}
	
	
}
