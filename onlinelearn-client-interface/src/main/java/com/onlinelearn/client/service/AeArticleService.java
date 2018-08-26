package com.onlinelearn.client.service;
import java.util.List;
import com.onlinelearn.pojo.AeArticle;
import com.onlinelearn.pojogroup.ArticleDetail;

import entity.PageResult;
/**
 * 服务层接口
 * @author yangminsen
 *
 */
public interface AeArticleService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<AeArticle> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(AeArticle article);
	
	
	/**
	 * 修改
	 */
	public void update(AeArticle article);
	

	/**
	 * 根据ID获取文章实体和该文章所有评论信息
	 * @param id
	 * @return ArticleDetail
	 */
	public ArticleDetail findOne(Integer id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Integer [] ids);
	
	
	/**
	 * 通过文章方向 dire 查找所有该方向的文章
	 * @param dire
	 * @return
	 */
	public List<AeArticle> findByDirection(String dire);
	
	
	/**
	 * 查询所有该用户(username)所写的文章
	 * @param username 传入用户username
	 * @return List< AeArticle > 
	 */
	public List<AeArticle> findAllArticleByUsername(String username);
	


}
