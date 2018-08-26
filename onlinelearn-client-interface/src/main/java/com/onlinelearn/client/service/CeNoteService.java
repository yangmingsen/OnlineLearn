package com.onlinelearn.client.service;

import java.util.List;

import com.onlinelearn.pojo.CeNote;
import com.onlinelearn.pojogroup.CeNoteGroup;
import com.onlinelearn.pojogroup.UrCourseNoteHistory;

import entity.PageResult;

public interface CeNoteService {
	/**
	 * 返回全部列表
	 * @return
	 */
	public List<CeNote> findAll();
	
	/**
	 * 根据课程id查找所有评论信息
	 * @return
	 */
	public List<CeNoteGroup> findAllByCourseId(Integer cid, Integer vid);
	
	
	/***
	 * 查询所有该用户(username)的课程笔记信息
	 * @param username 用户名
	 * @return UrCourseNoteHistory
	 */
	public List<UrCourseNoteHistory> findAllCourseNoteByUsername(String username);
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(CeNote note);
	
	
	/**
	 * 修改
	 */
	public void update(CeNote note);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public CeNote findOne(Integer noteId);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Integer [] noteIds);

}
