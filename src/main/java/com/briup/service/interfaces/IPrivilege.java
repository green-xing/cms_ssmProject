package com.briup.service.interfaces;

import java.util.List;

import com.briup.bean.Privilege;
import com.briup.utils.CustomerException;
import com.briup.vm.PrivilegeTree;

public interface IPrivilege {
	
	//查询所有的权限
	List<Privilege> findAll();
	
	//通过parentID查询权限
	List<Privilege> findByParentId(Integer id);
	
	//保存或者更新权限
	void saveOrUpdate(Privilege privilege) throws CustomerException;
	
	List<PrivilegeTree> findPrivilegeTree();
	
}
