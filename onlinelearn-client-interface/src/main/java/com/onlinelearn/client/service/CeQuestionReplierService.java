package com.onlinelearn.client.service;
import java.util.List;
import com.onlinelearn.pojo.CeQuestionReplier;

import entity.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface CeQuestionReplierService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<CeQuestionReplier> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(CeQuestionReplier questionReplier);
	
	
	/**
	 * 修改
	 */
	public void update(CeQuestionReplier questionReplier);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public CeQuestionReplier findOne(Integer id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Integer [] ids);

}
