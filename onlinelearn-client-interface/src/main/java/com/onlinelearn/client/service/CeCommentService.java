package com.onlinelearn.client.service;
import java.util.List;
import com.onlinelearn.pojo.CeComment;
import com.onlinelearn.pojogroup.CeCommentGroup;

import entity.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface CeCommentService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<CeComment> findAll();
	

	/***
	 * 通过课程cid和视频vid查找所有关于此课程的评论信息
	 * 注意：其中课程id必填而视频id可选
	 * @param cid 课程id
	 * @param vid 视频id
	 * @return
	 */
	public List<CeCommentGroup> findAllByCourseId(Integer cid, Integer vid);
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(CeComment comment);
	
	
	/**
	 * 修改
	 */
	public void update(CeComment comment);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public CeComment findOne(Integer id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Integer [] ids);

	
}
