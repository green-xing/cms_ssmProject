package com.briup.service.interfaces;

import java.util.List;

import javax.swing.text.DefaultEditorKit.CutAction;

import com.briup.bean.User;
import com.briup.bean.extend.UserExtend;
import com.briup.utils.CustomerException;

public interface IUser {
	
	//通过id查询用户
	UserExtend findById(Integer id);
	
	//查询所有用户
	List<User> findAll();
	
	//级联查询用户的角色
	List<UserExtend> cascadeRoleFindAll();
	
	//保存或者更新
	void saveOrUpdate(User user) throws CustomerException;
	
	//更新状态 ？？？
	void changeStatus(Integer id,String status) throws CustomerException;
	
	//通过id删除
	void deleteById(Integer id) throws CustomerException;
	
	//设置角色
	void setRoles(Integer id,List<Integer> roles);
	
}
