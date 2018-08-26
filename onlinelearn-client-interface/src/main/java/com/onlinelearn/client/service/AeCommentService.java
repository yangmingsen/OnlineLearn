package com.onlinelearn.client.service;
import java.util.List;
import com.onlinelearn.pojo.AeComment;

import entity.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface AeCommentService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<AeComment> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(AeComment comment);
	
	
	/**
	 * 修改
	 */
	public void update(AeComment comment);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public AeComment findOne(Integer id);
	
	
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
	public PageResult findPage(AeComment comment, int pageNum,int pageSize);
	
}
