package com.onlinelearn.pojogroup;

import java.io.Serializable;
import java.util.List;

import com.onlinelearn.pojo.AeArticle;


public class ArticleDetail implements Serializable {

	private AeArticle aeArticle;
	
	private List<AeComments> aeComments;
	
	public AeArticle getAeArticle() {
		return aeArticle;
	}
	public void setAeArticle(AeArticle aeArticle) {
		this.aeArticle = aeArticle;
	}
	public ArticleDetail(AeArticle aeArticle, List<AeComments> aeComments) {
		super();
		this.aeArticle = aeArticle;
		this.aeComments = aeComments;
	}
	public List<AeComments> getAeComments() {
		return aeComments;
	}
	public void setAeComments(List<AeComments> aeComments) {
		this.aeComments = aeComments;
	}
	
}
