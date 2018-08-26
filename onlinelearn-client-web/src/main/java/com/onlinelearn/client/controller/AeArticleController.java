package com.onlinelearn.client.controller;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.onlinelearn.pojo.AeArticle;
import com.onlinelearn.pojo.AeComment;
import com.onlinelearn.pojogroup.ArticleDetail;
import com.onlinelearn.client.service.AeArticleService;
import com.onlinelearn.client.service.AeCommentService;

import entity.PageResult;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class AeArticleController {

	@Reference
	private AeArticleService articleService;
	
	@Reference
	private AeCommentService aeCommentService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<AeArticle> findAll(){			
		return articleService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return articleService.findPage(page, rows);
	}
	
	/**
	 * 增加文章
	 * @param article
	 * @return
	 */
	@RequestMapping("/addArticle")
	public Result addArticle(@RequestBody AeArticle article){
		
		if(article.getAuthorId()==null || "".equals(article.getAuthorId()) ||
				article.getId()!=null ) {
			return new Result(false, "增加失败1");
		}
		
		try {
			articleService.add(article);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败2");
		}
	}
	
	/**
	 * 增加文章评论
	 * @param comment
	 * @return
	 */
	@RequestMapping("/addComment")
	public Result addComment(@RequestBody AeComment comment){
		try {
			aeCommentService.add(comment);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	
	
	/**
	 * 修改
	 * @param article
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody AeArticle article){
		try {
			articleService.update(article);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	/**
	 * 获取一个文章详情根据id
	 * @param id
	 * @return
	 */
	@RequestMapping("/findArticleOne")
	public ArticleDetail findArticleOne(Integer id){
		
		System.out.println("id = "+id);
		
		return articleService.findOne(id);		
	}
	
	/**
	 * 获取一个评论根据评论id (基本不用)
	 * @param id
	 * @return
	 */
	@RequestMapping("/findCommentOne")
	public AeComment findCommentOne(Integer id){
		return aeCommentService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Integer [] ids){
		try {
			articleService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
	/**
	 * 通过方向查找文章
	 * @param dire
	 * @return
	 */
	@RequestMapping("/findByDirection")
	public List<AeArticle> findByDirection(String dire) {
		
		if(dire == null || "".equals(dire) ) {
			return null;
		}
		
		return articleService.findByDirection(dire);
		
	}
	
}
