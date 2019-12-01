package com.briup.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.briup.bean.Article;
import com.briup.bean.ArticleExample;
import com.briup.bean.Comment;
import com.briup.bean.extend.ArticleExtend;
import com.briup.dao.ArticleMapper;
import com.briup.dao.CommentMapper;
import com.briup.dao.extend.ArticleExtendMapper;
import com.briup.dao.extend.CommentExtendMapper;
import com.briup.service.interfaces.IArticle;
import com.briup.utils.CustomerException;

//关于 article（文章） 的service
@Service
@Primary
public class AriticleImpl implements IArticle{
	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private ArticleExtendMapper articleExtendMapper;
	
//	@Autowired
//	private CommentMapper commentMapper;
	
	@Autowired
	private CommentExtendMapper commentExtendMapper;
	
	//article的单表查询所有
	@Override
	public List<Article> findAll() {
		
		List<Article> list = articleMapper.selectByExample(new ArticleExample());
		
		return list;
	}
	
	//article的单表查询 根据id查询
	@Override
	public Article findById(int id) {
		
		Article article = articleMapper.selectByPrimaryKey(id);
		
		return article;
	}
	
	//article的级联查询（查询所有）
	@Override
	public List<ArticleExtend> casecadeFindAll() {
		
		List<ArticleExtend> list = articleExtendMapper.selectAll();
		
		return list;
	}
	
	//articl的级联查询（根据id查询）
	@Override
	public ArticleExtend findByIdWithCG(int id) {

		ArticleExtend ae = articleExtendMapper.selectById(id);
		
		return ae;
	}
	
	//保存或者更新article
	@Override
	public void saveOrUpdate(Article article) throws CustomerException {
		if(article.getId() != null) {//更新操作
			articleMapper.updateByPrimaryKey(article);
		}else {//插入操作
			
			ArticleExample example = new ArticleExample();
			example.createCriteria().andTitleEqualTo(article.getTitle());
			List<Article> list = articleMapper.selectByExample(example);
			
			if(list.size()>0) {
				throw new CustomerException("标题不能重复");
			}
			
			article.setStatus(ArticleExtend.STATUS_UNCHECK);
			article.setPublishTime(new Date());
			articleMapper.insert(article);
		}
		
	}

	@Override
	public List<Comment> FindCommentByArticleId(int id) {
		
		List<Comment> comments = commentExtendMapper.selectByArticleId(id);
		
		return comments;
	}

}
