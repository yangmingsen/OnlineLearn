package com.onlinelearn.pojogroup;

import java.io.Serializable;

import com.onlinelearn.pojo.QoReplier;
import com.onlinelearn.pojo.UrUser;

public class PersonReplier implements Serializable{

	private UrUser urUser;
	private QoReplier qoReplier;
	public UrUser getUrUser() {
		return urUser;
	}
	public void setUrUser(UrUser urUser) {
		this.urUser = urUser;
	}
	public QoReplier getQoReplier() {
		return qoReplier;
	}
	public void setQoReplier(QoReplier qoReplier) {
		this.qoReplier = qoReplier;
	}
	public PersonReplier(UrUser urUser, QoReplier qoReplier) {
		super();
		this.urUser = urUser;
		this.qoReplier = qoReplier;
	}
	
	
}
