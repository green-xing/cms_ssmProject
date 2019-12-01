package com.briup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.briup.bean.Privilege;
import com.briup.bean.PrivilegeExample;
import com.briup.dao.PrivilegeMapper;
import com.briup.dao.extend.PrivilegeExtendMapper;
import com.briup.service.interfaces.IPrivilege;
import com.briup.utils.CustomerException;
import com.briup.vm.PrivilegeTree;

@Service
@Primary
public class PrivilegeImpl implements IPrivilege{
	
	@Autowired
	private PrivilegeMapper privilegeMa;
	@Autowired
	private PrivilegeExtendMapper privilegeEM;
	
	@Override
	public List<Privilege> findAll() {
		
		return privilegeMa.selectByExample(new PrivilegeExample());
	}

	@Override
	public List<Privilege> findByParentId(Integer id) {
		
		PrivilegeExample example = new PrivilegeExample();
		if(id == null) {
			example.createCriteria().andParentIdIsNull();
		}else {
			example.createCriteria().andParentIdEqualTo(id);
		}
		
		return privilegeMa.selectByExample(example);
	}

	@Override
	public void saveOrUpdate(Privilege privilege) throws CustomerException {
		
		if(privilege.getId() != null) {
			privilegeMa.updateByPrimaryKey(privilege);
		}else {
			privilegeMa.insert(privilege);
		}
		
	}

	@Override
	public List<PrivilegeTree> findPrivilegeTree() {
		
		return privilegeEM.selectAll();
	}

}
