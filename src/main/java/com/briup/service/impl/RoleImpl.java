package com.briup.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.briup.bean.Role;
import com.briup.bean.RoleExample;
import com.briup.bean.RolePrivilege;
import com.briup.bean.RolePrivilegeExample;
import com.briup.bean.extend.RoleExtend;
import com.briup.dao.RoleMapper;
import com.briup.dao.RolePrivilegeMapper;
import com.briup.dao.extend.RoleExtendMapper;
import com.briup.service.interfaces.IRole;
import com.briup.utils.CustomerException;

@Service
@Primary
public class RoleImpl implements IRole{
	
	@Autowired
	private RoleMapper roleMa;
	
	@Autowired
	private RoleExtendMapper roleME;
	
	@Autowired
	private RolePrivilegeMapper rolePrivilegeMa;
	
	@Override
	public void authorization(Integer roleId, List<Integer> privileges) {
		//根据roleid查询出所有的权限
		RolePrivilegeExample example = new RolePrivilegeExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		List<RolePrivilege> list = rolePrivilegeMa.selectByExample(example);
		
		//将list转换为privilegeIds的集合
		List<Integer> old_privs = new ArrayList<>();
		for(RolePrivilege rp : list) {
			old_privs.add(rp.getPrivilegeId());
		}
		
		//依次判断privilegeIds 是否存在old_privs，如果不在则插入
		for(Integer privId : privileges) {
			if(!old_privs.contains(privId)) {
				RolePrivilege rp = new RolePrivilege();
				rp.setRoleId(roleId);
				rp.setPrivilegeId(privId);
				rolePrivilegeMa.insert(rp);
			}
		}
		//依次判断 是否存在 old_priv 不存在新权限链表中，如果有则删除
		for(Integer privId : old_privs) {
			if(!privileges.contains(privId)) {
//				rolePrivilegeMa.deleteByPrimaryKey(privId);
				example.clear();
				example.createCriteria().andRoleIdEqualTo(roleId).andPrivilegeIdEqualTo(privId);
				rolePrivilegeMa.deleteByExample(example);
			}
		}
		
	}

	@Override
	public List<Role> findAll() {
		return roleMa.selectByExample(new RoleExample());
	}

	@Override
	public List<RoleExtend> casecadePrivilegeFindAll() {
		return roleME.selectAll();
	}

	@Override
	public void saveOrUpdate(Role baseRole) throws CustomerException {
		if(baseRole.getId() != null) {
			roleMa.updateByExample(baseRole, new RoleExample());
		}else {
			roleMa.insert(baseRole);
		}
		
	}

	@Override
	public void deleteById(Integer id) throws CustomerException {
		
		RoleExample example = new RoleExample();
		example.createCriteria().andIdEqualTo(id);
		
		List<Role> list = roleMa.selectByExample(example);
		if(list.size() == 0) {
			throw new CustomerException("要删除的角色不存在");
		}
		
		roleMa.deleteByExample(example);
		
	}

}
