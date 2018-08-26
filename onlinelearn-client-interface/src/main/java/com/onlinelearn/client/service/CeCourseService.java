package com.onlinelearn.client.service;
import java.util.List;
import java.util.Map;

import com.onlinelearn.pojo.CeCourse;
import com.onlinelearn.pojogroup.Course;

import entity.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface CeCourseService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<CeCourse> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 通过方向来查询课程
	 * @param deir
	 * @return
	 */
	public List<CeCourse> findByDirection(String deir);
	
	
	/**
	 * 通过课程分类查找课程
	 * @param category
	 * @return
	 */
	public List<CeCourse> findByCategory(String category);
	
	
	/**
	 * 增加
	*/
	public void add(CeCourse course);
	
	
	/**
	 * 修改
	 */
	public void update(CeCourse course);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public CeCourse findOne(Integer id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Integer [] ids);


	/**
	 * 查找该课程(id)的章节和章节对应的知识点视频
	 * 通过id的方式.最后以组合实体类
	 * Course{CeChapter, CeVideo}返回
	 * @param id
	 * @return
	 */
	public Course learnCourse(Integer id);
	
	
	/**
	 * 根据关键字keywords  进行搜索
	 * @param searchMap
	 * @return
	 */
	public Map<String, Object> search(Map searchMap);
	
	
	
	/**
	 * fun:通过vid查找视频id
	 * 
	 * 此方法是迫不得已增加，这是数据库设计问题
	 * 。当时就该把视频表中也把course_id加上 
	 * @param vid
	 * @return
	 */
	public Integer getCourseId(Integer vid);
	
	
	
	
	
}
