package com.briup.service.interfaces;

import java.util.List;

import com.briup.bean.Article;
import com.briup.bean.Comment;
import com.briup.bean.extend.ArticleExtend;
import com.briup.utils.CustomerException;

public interface IArticle {
	
	List<Article> findAll();
	Article findById(int id);
	
	//级联查询出文章相关的所有信息
	List<ArticleExtend> casecadeFindAll();
	
	//
	ArticleExtend findByIdWithCG(int id);
	
	//article的保存与更新
	void saveOrUpdate(Article article) throws CustomerException;
	
	//通过articleId 查找comment
	List<Comment> FindCommentByArticleId(int id);
	
}
