package com.onlinelearn.pojogroup;

import java.io.Serializable;

import com.onlinelearn.pojo.AeComment;
import com.onlinelearn.pojo.UrUser;

public class AeComments implements Serializable{

	private UrUser urUser;
	private AeComment aeComment;
	
	public UrUser getUrUser() {
		return urUser;
	}
	public void setUrUser(UrUser urUser) {
		this.urUser = urUser;
	}
	public AeComment getAeComment() {
		return aeComment;
	}
	public void setAeComment(AeComment aeComment) {
		this.aeComment = aeComment;
	}
	public AeComments(UrUser urUser, AeComment aeComment) {
		super();
		this.urUser = urUser;
		this.aeComment = aeComment;
	}
	
	
	
	
}
