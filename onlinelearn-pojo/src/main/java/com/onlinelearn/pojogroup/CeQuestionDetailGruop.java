package com.onlinelearn.pojogroup;

import java.io.Serializable;
import java.util.List;

import com.onlinelearn.pojo.CeQuestion;
import com.onlinelearn.pojo.CeQuestionReplier;
import com.onlinelearn.pojo.UrUser;

/***
 * 课程问题详情组合实体类
 * 包含UrUser 、CeQuestion 、List<CeQuestionReplier> 
 * 关系 一个问题对应多个回复评论   CeQuestion => List<CeQuestionReplier> 
 * 一个问题对应一个提问作者 CeQuestion =>  UrUser
 * @author yangmingsen
 *
 */
public class CeQuestionDetailGruop implements Serializable {

	private String videoname;
	private UrUser urUser; //作者信息
	private CeQuestion ceQuestion;//作者提问信息
	private List<CeQuestionReplier> questionRepliers;//该问题回复信息
	
	public String getVideoname() {
		return videoname;
	}
	public void setVideoname(String videoname) {
		this.videoname = videoname;
	}
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
	public List<CeQuestionReplier> getQuestionRepliers() {
		return questionRepliers;
	}
	public void setQuestionRepliers(List<CeQuestionReplier> questionRepliers) {
		this.questionRepliers = questionRepliers;
	}
	
	public CeQuestionDetailGruop(String videoname, UrUser urUser, CeQuestion ceQuestion,
			List<CeQuestionReplier> questionRepliers) {
		super();
		this.videoname = videoname;
		this.urUser = urUser;
		this.ceQuestion = ceQuestion;
		this.questionRepliers = questionRepliers;
	}

	
	
	
}
