package com.briup.service.interfaces;

import java.util.List;

import com.briup.bean.Category;
import com.briup.utils.CustomerException;

public interface ICategory {
	
	List<Category> findAll();
	
	void saveOrUpdate(Category category) throws CustomerException;
	
	void deleteById(Integer id)throws CustomerException;
	
	//批量删除
	void batchDelete(Integer[] ids) throws CustomerException;
	
}
