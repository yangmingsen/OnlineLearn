package com.onlinelearn.pojogroup;

import java.io.Serializable;
import java.util.List;

import com.onlinelearn.pojo.QoQuestion;
import com.onlinelearn.pojo.QoReplier;
import com.onlinelearn.pojo.UrUser;


/**
 * 问题详细页组合实体类包含(提问人提问实体,提问人信息实体,回复评论实体组合类(List[PersonReplier{UrUrser,QoReplier}]))
 * @author yangmingsen
 *
 */
public class Detail implements Serializable{

	private QoQuestion qoQuestion;
	private UrUser urUser;
	private List<PersonReplier> qoRepliers;
	
	public QoQuestion getQoQuestion() {
		return qoQuestion;
	}
	public void setQoQuestion(QoQuestion qoQuestion) {
		this.qoQuestion = qoQuestion;
	}
	public UrUser getUrUser() {
		return urUser;
	}
	public void setUrUser(UrUser urUser) {
		this.urUser = urUser;
	}
	public List<PersonReplier> getQoRepliers() {
		return qoRepliers;
	}
	public void setQoRepliers(List<PersonReplier> qoRepliers) {
		this.qoRepliers = qoRepliers;
	}
	public Detail(QoQuestion qoQuestion, UrUser urUser, List<PersonReplier> qoRepliers) {
		super();
		this.qoQuestion = qoQuestion;
		this.urUser = urUser;
		this.qoRepliers = qoRepliers;
	}
	
	
}
