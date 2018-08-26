package com.onlinelearn.pojogroup;

import java.io.Serializable;

import com.onlinelearn.pojo.CeQuestion;
import com.onlinelearn.pojo.CeVideo;
import com.onlinelearn.pojo.UrUser;

/**
 * 课程问答组合实体类包含（用户信息UrUser，用户提问信息CeQuestion,
 * 课程视频CeVideo信息,回答数）
 * @author yangmingsen
 *
 */
public class CeQuestionGroup implements Serializable {

	private UrUser urUser;//用户模型
	private CeQuestion ceQuestion;//问题模型
	private CeVideo ceVideo;//视频模型
	private int replierNum;//回答数
	
	public UrUser getUrUser() {
		return urUser;
	}
	public void setUrUser(UrUser urUser) {
		this.urUser = urUser;
	}
	public CeQuestion getCeQuestion() {
		return ceQuestion;
	}
	public void setCeQuestion(CeQuestion ceQuestion) {
		this.ceQuestion = ceQuestion;
	}
	public CeVideo getCeVideo() {
		return ceVideo;
	}
	public void setCeVideo(CeVideo ceVideo) {
		this.ceVideo = ceVideo;
	}
	public int getReplierNum() {
		return replierNum;
	}
	public void setReplierNum(int replierNum) {
		this.replierNum = replierNum;
	}
	public CeQuestionGroup(UrUser urUser, CeQuestion ceQuestion, CeVideo ceVideo, int replierNum) {
		super();
		this.urUser = urUser;
		this.ceQuestion = ceQuestion;
		this.ceVideo = ceVideo;
		this.replierNum = replierNum;
	}
	
	
	
}
