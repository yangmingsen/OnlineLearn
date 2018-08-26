package com.onlinelearn.pojogroup;

import java.io.Serializable;
import java.util.List;

import com.onlinelearn.pojo.CeCourse;

/**
 * 课程组合实体类包含课程,章节,视频
 * @author yangmingsen
 *
 */
public class Course implements Serializable{
	
	private CeCourse ceCourse;
	private List<Chapter> chapters;
	
	public CeCourse getCeCourse() {
		return ceCourse;
	}
	public void setCeCourse(CeCourse ceCourse) {
		this.ceCourse = ceCourse;
	}
	public List<Chapter> getChapters() {
		return chapters;
	}
	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}
	public Course(CeCourse ceCourse, List<Chapter> chapters) {
		super();
		this.ceCourse = ceCourse;
		this.chapters = chapters;
	}
	
}
