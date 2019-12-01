package com.briup.service.interfaces;

import java.util.List;

import com.briup.bean.Role;
import com.briup.bean.extend.RoleExtend;
import com.briup.utils.CustomerException;

public interface IRole {
	
	//授权
	void authorization(Integer roleId,List<Integer> privileges);
	
	//查询所有角色	
	List<Role> findAll();
	
	//查询角色级联权限
	List<RoleExtend> casecadePrivilegeFindAll();
	
	//保存或更新角色信息
	void saveOrUpdate(Role baseRole)throws CustomerException;
	
	//通过id删除角色信息
	void deleteById(Integer id)throws CustomerException;
	
	
}
