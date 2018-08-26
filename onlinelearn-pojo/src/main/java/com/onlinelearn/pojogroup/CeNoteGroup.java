package com.onlinelearn.pojogroup;

import java.io.Serializable;

import com.onlinelearn.pojo.CeNote;
import com.onlinelearn.pojo.CeVideo;
import com.onlinelearn.pojo.UrUser;

/***
 * 这是课程笔记组合实体类模型
 * 一个笔记包含用户信息,视频信息,课程笔记模型
 * 关系 都是 1 <=> 1 <=> 1
 * @author yangmingsen
 *
 */
public class CeNoteGroup implements Serializable {

	private UrUser urUser;
	private CeVideo ceVideo;
	private CeNote ceNote;//课程笔记模型
	
	public UrUser getUrUser() {
		return urUser;
	}
	public void setUrUser(UrUser urUser) {
		this.urUser = urUser;
	}
	public CeVideo getCeVideo() {
		return ceVideo;
	}
	public void setCeVideo(CeVideo ceVideo) {
		this.ceVideo = ceVideo;
	}
	public CeNote getCeNote() {
		return ceNote;
	}
	public void setCeNote(CeNote ceNote) {
		this.ceNote = ceNote;
	}
	public CeNoteGroup(UrUser urUser, CeVideo ceVideo, CeNote ceNote) {
		super();
		this.urUser = urUser;
		this.ceVideo = ceVideo;
		this.ceNote = ceNote;
	}
	
}
