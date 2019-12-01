package com.briup.web.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.bean.Article;
import com.briup.bean.Category;
import com.briup.service.interfaces.ICategory;
import com.briup.utils.Message;
import com.briup.utils.MessageUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/cms_jd1911/category")
public class CategoryController {
	
	@Autowired
	private ICategory icategory;
	
	@ApiOperation(value="查询所有栏目")
	@GetMapping("findAll")
	public Message findAll(){
		
		List<Category> list = icategory.findAll();
		
		return MessageUtil.success(list);
		
	}
	
	@ApiOperation(value="通过id删除栏目")
	@GetMapping("delteById")
	public Message deleteById(Integer id) {
		icategory.deleteById(id);
		return MessageUtil.success("删除成功");
	}
	
	@ApiOperation(value="通过id 批量删除栏目")
	@PostMapping("batchDelete")
	public Message batchdelete(Integer[] ids) {
		
		icategory.batchDelete(ids);
		
		return MessageUtil.success("批量删除成功");
		
	}
	
	@ApiOperation(value="保存或更新栏目")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="主键id",paramType = "form"),
		@ApiImplicitParam(name="name",value="栏目名称",paramType = "form",required = true),
		@ApiImplicitParam(name="description",value="栏目描述",paramType = "form"),
		@ApiImplicitParam(name="no",value="栏目序号",paramType = "form"),
		@ApiImplicitParam(name="parentId",value="父栏目id",paramType = "form")
	})
	@PostMapping("saveOrUpdate")
	public Message saveOrUpdate(
			Integer id,
			@NotNull String name,
			String description,
			Integer no,
			Integer parentId) {
		
		Category category = new Category();
		
		category.setId(id);
		category.setName(name);
		category.setDescription(description);
		category.setNo(no);
		category.setParentId(parentId);
		
		icategory.saveOrUpdate(category);
		
		return MessageUtil.success("更新成功");
		
	}
	
	
	
}
