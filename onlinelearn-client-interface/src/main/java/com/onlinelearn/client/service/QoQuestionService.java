package com.onlinelearn.client.service;
import java.util.List;
import com.onlinelearn.pojo.QoQuestion;

import entity.PageResult;
/**
 * 服务层接口
 * @author yangmingsen
 *
 */
public interface QoQuestionService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<QoQuestion> findAll();
	
	
	/***
	 * 通过username查找该用户的所有提问()
	 * @param username
	 * @return
	 */
	public List<QoQuestion> findAllByUsername(String username);
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(QoQuestion question);
	
	
	/**
	 * 修改
	 */
	public void update(QoQuestion question);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public QoQuestion findOne(Integer id);
	
	
	/***
	 * 通过问题分类名称查找关于该分类测所有问题
	 * @param cery 传入的分类名称 
	 * @return
	 */
	public  List<QoQuestion> findAllByCategory(String cery);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Integer [] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(QoQuestion question, int pageNum,int pageSize);
	
}
