package com.onlinelearn.pojogroup;

import java.io.Serializable;

import com.onlinelearn.pojo.CeCourse;
import com.onlinelearn.pojo.CeNote;
import com.onlinelearn.pojo.CeVideo;

/***
 * 用户课程笔记历史模型
 * @author yangmingsen
 *
 */
public class UrCourseNoteHistory implements Serializable {

	private CeCourse course;//课程模型
	private CeVideo video;//视频模型
	private CeNote note;//笔记模型
	
	public CeCourse getCourse() {
		return course;
	}
	public void setCourse(CeCourse course) {
		this.course = course;
	}
	public CeVideo getVideo() {
		return video;
	}
	public void setVideo(CeVideo video) {
		this.video = video;
	}
	public CeNote getNote() {
		return note;
	}
	public void setNote(CeNote note) {
		this.note = note;
	}
	public UrCourseNoteHistory(CeCourse course, CeVideo video, CeNote note) {
		super();
		this.course = course;
		this.video = video;
		this.note = note;
	}
	
	
	
}
