package com.briup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briup.bean.Category;
import com.briup.bean.CategoryExample;
import com.briup.dao.CategoryMapper;
import com.briup.service.interfaces.ICategory;
import com.briup.utils.CustomerException;

@Service
@Primary
public class CategoryImpl implements ICategory{
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	public List<Category> findAll() {
		
		return categoryMapper.selectByExample(new CategoryExample());
	}

	@Override
	public void saveOrUpdate(Category category) throws CustomerException {
		
		if(category.getId() != null) {
			categoryMapper.updateByPrimaryKey(category);
		}else {
			//判断是否重名
			CategoryExample example = new CategoryExample();
			example.createCriteria().andNameEqualTo(category.getName());
			List<Category> list = categoryMapper.selectByExample(example);
			if(list.size() > 0) {
				throw new CustomerException("该栏目已存在");
			}
			categoryMapper.insert(category);
		}
		
	}

	@Override
	@Transactional
	public void deleteById(Integer id) throws CustomerException {
		Category category = categoryMapper.selectByPrimaryKey(id);
		if(category == null) {
			throw new CustomerException("要删除的栏目不存在");
		}
		categoryMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	@Transactional
	public void batchDelete(Integer[] ids) throws CustomerException {
		
		for(Integer id : ids) {
			this.deleteById(id);
		}
		
	}

}
