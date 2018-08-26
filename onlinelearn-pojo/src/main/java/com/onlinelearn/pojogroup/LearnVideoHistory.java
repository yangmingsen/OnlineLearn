package com.onlinelearn.pojogroup;

import java.io.Serializable;

import com.onlinelearn.pojo.CeCourse;
import com.onlinelearn.pojo.CeVideo;
import com.onlinelearn.pojo.UrLearnCourseHistory;


/**
 * 组合实体类，使用{UrLearnCourseHistory,CeCourse,CeVideo}作为对象组合
 * 返回一个用户学习视频的历史记录
 * @author yangmingsen
 *
 */
public class LearnVideoHistory implements Serializable{

	UrLearnCourseHistory urLearnCourseHistory;//用户历史记录
	CeCourse ceCourse;//课程信息
	CeVideo ceVideo;//视频信息
	
	public LearnVideoHistory(UrLearnCourseHistory urLearnCourseHistory, CeCourse ceCourse, CeVideo ceVideo) {
		super();
		this.urLearnCourseHistory = urLearnCourseHistory;
		this.ceCourse = ceCourse;
		this.ceVideo = ceVideo;
	}

	public UrLearnCourseHistory getUrLearnCourseHistory() {
		return urLearnCourseHistory;
	}

	public void setUrLearnCourseHistory(UrLearnCourseHistory urLearnCourseHistory) {
		this.urLearnCourseHistory = urLearnCourseHistory;
	}

	public CeCourse getCeCourse() {
		return ceCourse;
	}

	public void setCeCourse(CeCourse ceCourse) {
		this.ceCourse = ceCourse;
	}

	public CeVideo getCeVideo() {
		return ceVideo;
	}

	public void setCeVideo(CeVideo ceVideo) {
		this.ceVideo = ceVideo;
	}
	
	
	
	
	
}
