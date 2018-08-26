package com.onlinelearn.client.service;
import java.util.List;
import com.onlinelearn.pojo.UrLearnCourseHistory;
import com.onlinelearn.pojogroup.LearnVideoHistory;

import entity.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface UrLearnCourseHistoryService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<UrLearnCourseHistory> findAll();
	
	
	/**
	 * 通过用户id(username)找到所有用户学习的视频学习记录
	 * @param usr 传入用户名(username)
	 * @return 组合实体类LearnVideoHistory
	 */
	public List<LearnVideoHistory> findAllByUsername(String userId);
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(UrLearnCourseHistory learnCourseHistory);
	
	
	/**
	 * 修改
	 */
	public void update(UrLearnCourseHistory learnCourseHistory);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public UrLearnCourseHistory findOne(Integer id);
	
	
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
	public PageResult findPage(UrLearnCourseHistory learnCourseHistory, int pageNum,int pageSize);
	
	
	/***
	 * 根据用户名和视频vid判断该视频用户是否已经学习
	 * 如果true 表示该用户已学习该视频 false反之
	 * @param username 用户名
	 * @param vid 视频id
	 * @return 
	 */
	public boolean isCourseLearning(String username,Integer vid);
	
	
	/***
	 * 根据用户名和视频vid查找历史信息
	 * 如果存在则返回实体  否则返回null
	 * @param username 用户名
	 * @param vid 视频id
	 * @return
	 */
	public UrLearnCourseHistory findOneByUidAndVid(String username,Integer vid);
	
}
