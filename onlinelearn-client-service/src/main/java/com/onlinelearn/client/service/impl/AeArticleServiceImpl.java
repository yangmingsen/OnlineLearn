package com.onlinelearn.client.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onlinelearn.mapper.AeArticleMapper;
import com.onlinelearn.mapper.AeCommentMapper;
import com.onlinelearn.mapper.TbArticleDirectionMapper;
import com.onlinelearn.mapper.UrUserMapper;
import com.onlinelearn.pojo.AeArticle;
import com.onlinelearn.pojo.AeArticleExample;
import com.onlinelearn.pojo.AeComment;
import com.onlinelearn.pojo.AeCommentExample;
import com.onlinelearn.pojo.TbArticleDirection;
import com.onlinelearn.pojo.TbArticleDirectionExample;
import com.onlinelearn.pojo.UrUser;
import com.onlinelearn.pojo.TbArticleDirectionExample.Criteria;
import com.onlinelearn.pojo.UrUserExample;
import com.onlinelearn.pojogroup.AeComments;
import com.onlinelearn.pojogroup.ArticleDetail;
import com.onlinelearn.client.service.AeArticleService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class AeArticleServiceImpl implements AeArticleService {

	@Autowired
	private AeArticleMapper articleMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<AeArticle> findAll() {
		return articleMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<AeArticle> page=   (Page<AeArticle>) articleMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(AeArticle article) {
		
		article.setFavourNum(0);
		article.setBrowseNum(0);
		article.setTime(new Date());
		articleMapper.insert(article);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(AeArticle article){
		articleMapper.updateByPrimaryKey(article);
	}	
	
	@Autowired
	private AeCommentMapper commentMapper;
	
	@Autowired
	private UrUserMapper urUserMapper;
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public ArticleDetail findOne(Integer id){
		
		//1.先向数据库中根据id查找该文章信息
		AeArticle aeArticle = articleMapper.selectByPrimaryKey(id);
		
		//2.再根据id向数据库中查找该文章评论信息
		AeCommentExample aeCommentExample = new AeCommentExample();
		com.onlinelearn.pojo.AeCommentExample.Criteria criteria = aeCommentExample.createCriteria();
		criteria.andArticleIdEqualTo(id);
		List<AeComment> comments = commentMapper.selectByExample(aeCommentExample);

		ArrayList<AeComments> aeComments = new ArrayList<AeComments>();
		//3.根据得到到comments.commenterId for循环然后拿到分别对应的用户信息
		for(AeComment commenter : comments ) {
			
			UrUser urUser = urUserMapper.selectByUsername(commenter.getCommenterId());
			urUser.setPassword("Hamapi");
			aeComments.add(new AeComments(urUser, commenter));
		}
		
		return new ArticleDetail(aeArticle, aeComments);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer [] ids) {
		for(Integer id:ids){
			articleMapper.deleteByPrimaryKey(id);
		}		
	}
	
	@Autowired
	private TbArticleDirectionMapper tbArticleDirectionMapper;
	
	/***
	 * 首先通过方向(dire)向数据库查找该dire对应的id
	 * 然后再通过id查找所有文章
	 */
	public List<AeArticle> findByDirection(String dire) {
		
		//1.先通过dire查找该方向 id 
		TbArticleDirectionExample tbArticleDirectionExample = new TbArticleDirectionExample();
		Criteria criteria = tbArticleDirectionExample.createCriteria();
		criteria.andNameEqualTo(dire);
		List<TbArticleDirection> articleDirection = tbArticleDirectionMapper.selectByExample(tbArticleDirectionExample);
		
		if(articleDirection.size() == 0 ) {//如果该方向不存在 返回null
			return null;
		}
		
		//2.再通过id查找所有该方向（dire）的文章
		AeArticleExample aeArticleExample = new AeArticleExample();
		com.onlinelearn.pojo.AeArticleExample.Criteria criteria2 = aeArticleExample.createCriteria();
		criteria2.andDirectionIdEqualTo(articleDirection.get(0).getId());
		return articleMapper.selectByExample(aeArticleExample);
		
	}
	
	/***
	 * 通过用户名查找所有该用户所写的文章
	 */
	public List<AeArticle> findAllArticleByUsername(String username) {
		
		AeArticleExample aeArticleExample = new AeArticleExample();
		com.onlinelearn.pojo.AeArticleExample.Criteria criteria = aeArticleExample.createCriteria();
		criteria.andAuthorIdEqualTo(username);
		
		return articleMapper.selectByExample(aeArticleExample);
		
	}
	
}
