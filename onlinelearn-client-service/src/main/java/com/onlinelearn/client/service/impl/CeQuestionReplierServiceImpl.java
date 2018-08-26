package com.onlinelearn.client.service.impl;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onlinelearn.mapper.CeQuestionReplierMapper;
import com.onlinelearn.pojo.CeQuestionReplier;
import com.onlinelearn.pojo.CeQuestionReplierExample;
import com.onlinelearn.pojo.CeQuestionReplierExample.Criteria;
import com.onlinelearn.client.service.CeQuestionReplierService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class CeQuestionReplierServiceImpl implements CeQuestionReplierService {

	@Autowired
	private CeQuestionReplierMapper questionReplierMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<CeQuestionReplier> findAll() {
		return questionReplierMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<CeQuestionReplier> page=   (Page<CeQuestionReplier>) questionReplierMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(CeQuestionReplier questionReplier) {
		
		questionReplier.setFavourNum(0);
		questionReplier.setTime(new Date());
		
		questionReplierMapper.insert(questionReplier);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(CeQuestionReplier questionReplier){
		questionReplierMapper.updateByPrimaryKey(questionReplier);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public CeQuestionReplier findOne(Integer id){
		return questionReplierMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			questionReplierMapper.deleteByPrimaryKey(id);
		}		
	}
	

	
}
