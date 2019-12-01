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
import com.briup.bean.Comment;
import com.briup.bean.extend.ArticleExtend;
import com.briup.service.interfaces.IArticle;
import com.briup.utils.Message;
import com.briup.utils.MessageUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/cms_jd1911/article")
public class ArticleController {
	
	@Autowired
	private IArticle article;
	
	@ApiOperation(value="查询所有文章")
	@GetMapping("findAll")
	public Message findAllOfArticle(){
		
		List<Article> list = article.findAll();
		
		return MessageUtil.success(list);
		
	}
	
	@ApiOperation(value="级联查询所有文章",notes="级联所属栏目，作者")
	@GetMapping("casecadefindAll")
	public Message selectAllOfAE(){
		
		List<ArticleExtend> list = article.casecadeFindAll();
		
		return MessageUtil.success(list);
		
	}
	
	@GetMapping("selectById")
	public Message selectById(int id) {
		return MessageUtil.success(article.findById(id));
	}
	
	@ApiOperation(value="通过id查询")
	@ApiImplicitParams(
			@ApiImplicitParam(name="id",value="主键",paramType = "query")
			)
	@GetMapping("selectByIdWithCG")
	public Message selectByIdWithCG(int id) {
		return MessageUtil.success(article.findByIdWithCG(id));
	}
	
	@ApiOperation(value="保存或者更新文章",notes="如果参数中有id则是更新，没有事保存操作")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="编号",paramType="form",required = false),
		@ApiImplicitParam(name="title",value="标题",paramType="form",required = true),
		@ApiImplicitParam(name="content",value="内容",paramType="form",required = true),
		@ApiImplicitParam(name="source",value="原内容",paramType="form",required = false),
		@ApiImplicitParam(name="categoryId",value="所属栏目id",paramType="form",required = true),
		@ApiImplicitParam(name="authorId",value="所属作者id",paramType="form",required = false)
	}
	)
	@PostMapping("saveOrUpdateArticle")
	public Message saveOrUpdateArticle(
			Integer id,
		@NotNull String title,
		@NotNull String content,
			String source,
		@NotNull Integer categoryId,
			Integer authorId) {
		Article ar = new Article();
		ar.setId(id);
		ar.setTitle(title);
		ar.setContent(content);
		ar.setSource(source);
		ar.setCategoryId(categoryId);
		ar.setAuthorId(authorId);
		article.saveOrUpdate(ar);
		return MessageUtil.success("更新成功");
	}
	
	@GetMapping("selectCommentByArticleId")
	public Message selectByArticleId(int articleId) {
		
		List<Comment> comments = article.FindCommentByArticleId(articleId);
		
		return MessageUtil.success(comments);
	}
	
}
