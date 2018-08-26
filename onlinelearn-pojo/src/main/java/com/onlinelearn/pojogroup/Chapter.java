package com.onlinelearn.pojogroup;

import java.io.Serializable;
import java.util.List;

import com.onlinelearn.pojo.CeChapter;
import com.onlinelearn.pojo.CeVideo;

/**
 * 组合实体类包含{CeChapter,
 * CeVideo} 将章节和章节视频放在一起传输到
 * 前端,这里多个章节，多个视频，而每个章节都
 * 对应多个视频，章节和视频之间通过chapter_id
 * 进行联系
 * @author yangmingsen
 *
 */
public class Chapter implements Serializable{
	
	private CeChapter ceChapter; //1个章节
	private List<CeVideo> ceVideo;//多个视频
	
	
	public Chapter(CeChapter ceChapter, List<CeVideo> ceVideo) {
		super();
		this.ceChapter = ceChapter;
		this.ceVideo = ceVideo;
	}
	public CeChapter getCeChapter() {
		return ceChapter;
	}
	public void setCeChapter(CeChapter ceChapter) {
		this.ceChapter = ceChapter;
	}
	public List<CeVideo> getCeVideo() {
		return ceVideo;
	}
	public void setCeVideo(List<CeVideo> ceVideo) {
		this.ceVideo = ceVideo;
	}
}
