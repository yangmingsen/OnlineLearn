package com.onlinelearn.client.service.impl;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onlinelearn.mapper.AeCommentMapper;
import com.onlinelearn.pojo.AeComment;
import com.onlinelearn.pojo.AeCommentExample;
import com.onlinelearn.pojo.AeCommentExample.Criteria;
import com.onlinelearn.client.service.AeCommentService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class AeCommentServiceImpl implements AeCommentService {

	@Autowired
	private AeCommentMapper commentMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<AeComment> findAll() {
		return commentMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<AeComment> page=   (Page<AeComment>) commentMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(AeComment comment) {
		
		comment.setFavourNum(0);
		comment.setTime(new Date());
		commentMapper.insert(comment);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(AeComment comment){
		commentMapper.updateByPrimaryKey(comment);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public AeComment findOne(Integer id){
		return commentMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			commentMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(AeComment comment, int pageNum, int pageSize) {
		return null;
	}
	
}
