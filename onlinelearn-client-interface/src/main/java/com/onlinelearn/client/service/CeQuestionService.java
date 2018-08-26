package com.onlinelearn.client.service;
import java.util.List;
import com.onlinelearn.pojo.CeQuestion;
import com.onlinelearn.pojogroup.CeQuestionDetailGruop;
import com.onlinelearn.pojogroup.CeQuestionGroup;

import entity.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface CeQuestionService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<CeQuestion> findAll();
	
	
	/**
	 * 通过课程id和视频id查找关于该课程所有问答信息
	 * 注意：其中课程id必填而视频id可选
	 * @param cid 课程id
	 * @param vid 视频id
	 * @return
	 */
	public List<CeQuestionGroup> findAllByCourseId(Integer id,Integer vid);
	
	
	
	/***
	 * 通过问题qid查找对应该问题的详细数据
	 * @param id
	 * @return
	 */
	public CeQuestionDetailGruop findQuestionDetailById(Integer qid);
	
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(CeQuestion question);
	
	
	/**
	 * 修改
	 */
	public void update(CeQuestion question);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public CeQuestion findOne(Integer id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Integer [] ids);


	
}
