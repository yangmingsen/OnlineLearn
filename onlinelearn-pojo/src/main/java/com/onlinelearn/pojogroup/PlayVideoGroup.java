package com.onlinelearn.pojogroup;

import java.io.Serializable;
import java.util.List;

import com.onlinelearn.pojo.CeVideo;


/***
 * 视频播放页实体组合类
 * 关系：一个视频对应多个问答和多个评论信息和多个笔记信息
 * @author yangmingsen
 *
 */
public class PlayVideoGroup implements Serializable{

	private Integer courseId;//课程id
	private CeVideo ceVideo;//该视频信息
	private List<CeQuestionGroup> ceQuestionGroups;//该视频的所有问答信息
	private List<CeCommentGroup> ceCommentGroups;//该视频的所有评论信息
	private List<CeNoteGroup> ceNoteGroups;//该视频所有笔记信息
	
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public CeVideo getCeVideo() {
		return ceVideo;
	}
	public void setCeVideo(CeVideo ceVideo) {
		this.ceVideo = ceVideo;
	}
	public List<CeQuestionGroup> getCeQuestionGroups() {
		return ceQuestionGroups;
	}
	public void setCeQuestionGroups(List<CeQuestionGroup> ceQuestionGroups) {
		this.ceQuestionGroups = ceQuestionGroups;
	}
	public List<CeCommentGroup> getCeCommentGroups() {
		return ceCommentGroups;
	}
	public void setCeCommentGroups(List<CeCommentGroup> ceCommentGroups) {
		this.ceCommentGroups = ceCommentGroups;
	}
	public List<CeNoteGroup> getCeNoteGroups() {
		return ceNoteGroups;
	}
	public void setCeNoteGroups(List<CeNoteGroup> ceNoteGroups) {
		this.ceNoteGroups = ceNoteGroups;
	}
	public PlayVideoGroup(Integer courseId, CeVideo ceVideo, List<CeQuestionGroup> ceQuestionGroups,
			List<CeCommentGroup> ceCommentGroups, List<CeNoteGroup> ceNoteGroups) {
		super();
		this.courseId = courseId;
		this.ceVideo = ceVideo;
		this.ceQuestionGroups = ceQuestionGroups;
		this.ceCommentGroups = ceCommentGroups;
		this.ceNoteGroups = ceNoteGroups;
	}
	
	

	
	
	
}
