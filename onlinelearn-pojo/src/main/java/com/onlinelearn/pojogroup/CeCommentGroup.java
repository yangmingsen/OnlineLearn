package com.onlinelearn.pojogroup;

import java.io.Serializable;

import com.onlinelearn.pojo.CeComment;
import com.onlinelearn.pojo.CeVideo;
import com.onlinelearn.pojo.UrUser;


/***
 * 课程评论组合实体类,包含(用户信息UrUser,
 * 评论信息CeComment,视频信息CeVideo)
 * @author yangmingsen
 *
 */
public class CeCommentGroup implements Serializable {

	private UrUser urUser;//用户模型
	private CeComment ceComment;//评论模型
	private CeVideo ceVideo;//视频模型
	public UrUser getUrUser() {
		return urUser;
	}
	public void setUrUser(UrUser urUser) {
		this.urUser = urUser;
	}
	public CeComment getCeComment() {
		return ceComment;
	}
	public void setCeComment(CeComment ceComment) {
		this.ceComment = ceComment;
	}
	public CeVideo getCeVideo() {
		return ceVideo;
	}
	public void setCeVideo(CeVideo ceVideo) {
		this.ceVideo = ceVideo;
	}
	public CeCommentGroup(UrUser urUser, CeComment ceComment, CeVideo ceVideo) {
		super();
		this.urUser = urUser;
		this.ceComment = ceComment;
		this.ceVideo = ceVideo;
	}
	
	
	
}
