package com.briup.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.briup.bean.User;
import com.briup.bean.UserExample;
import com.briup.bean.UserRole;
import com.briup.bean.UserRoleExample;
import com.briup.bean.extend.UserExtend;
import com.briup.dao.UserMapper;
import com.briup.dao.UserRoleMapper;
import com.briup.dao.extend.UserExtendMapper;
import com.briup.service.interfaces.IUser;
import com.briup.utils.CustomerException;

@Service
@Primary
public class UserImpl implements IUser{
	
	@Autowired
	private UserExtendMapper userEXMapper;
	@Autowired
	private UserMapper userMa;
	@Autowired
	private UserRoleMapper userRoleMa;

	@Override
	public UserExtend findById(Integer id) {
		
		return userEXMapper.selectById(id);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userMa.selectByExample(new UserExample());
	}

	@Override
	public List<UserExtend> cascadeRoleFindAll() {
		// TODO Auto-generated method stub
		return userEXMapper.selectAll();
	}

	@Override
	public void saveOrUpdate(User user) throws CustomerException {
		
		if(user.getId() != null) {
			userMa.updateByPrimaryKey(user);
		}else {
			//判断用户是否被占用
			UserExample example = new UserExample();
			example.createCriteria().andUsernameEqualTo(user.getUsername());
			List<User> list = userMa.selectByExample(example);
			if(list.size()>0) {
				throw new CustomerException("用户名已存在");
			}
			user.setRegisterTime(new Date());
			user.setStatus(UserExtend.STATUS_NORMAL);
			userMa.insert(user);
		}
		
	}

	@Override
	public void changeStatus(Integer id, String status) throws CustomerException {
		
		UserExtend user = this.findById(id);
		if(user == null) {
			throw new CustomerException("该用户不存在");
		}
		
		user.setStatus(status);
		userMa.updateByPrimaryKey(user);
		
	}

	@Override
	public void deleteById(Integer id) throws CustomerException {
		
		UserExtend user= this.findById(id);
		if(user == null) {
			throw new CustomerException("该用户不存在");
		}
		
		userMa.deleteByPrimaryKey(id);
		
	}

	@Override
	public void setRoles(Integer id, List<Integer> roles) {
		UserRoleExample example = new UserRoleExample();
		
		example.createCriteria().andUserIdEqualTo(id);
		//用户角色关系，获取所有老的角色
		List<UserRole> list = userRoleMa.selectByExample(example);
		ArrayList<Integer> oldRoleIds = new ArrayList<>();
		Iterator<UserRole> iterator = list.iterator();
		while(iterator.hasNext()) {
			oldRoleIds.add(iterator.next().getUserId());
		}
		
		//依次判断角色是否存在于list中，如果不存在则添加
		for(Integer roleId : roles) {
			if(!oldRoleIds.contains(roleId)) {
				UserRole userRole = new UserRole();
				userRole.setUserId(id);
				userRole.setRoleId(roleId);
				userRoleMa.insert(userRole);
			}
		}
		
		//依次判断老的角色是否存在于roles中，如果存在则删除
		for(UserRole ur : list) {
			if(!roles.contains(ur.getRoleId())) {
				userRoleMa.deleteByPrimaryKey(ur.getId());
			}
		}
		
		
		
		
		
	}

}
