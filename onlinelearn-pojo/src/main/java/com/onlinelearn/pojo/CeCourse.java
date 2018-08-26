package com.onlinelearn.pojo;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

public class CeCourse implements Serializable{
    
	@Id
	@Field("source_id")
	private Integer sourceId;
	
	@Field("course_description")
    private String description;

	@Field("course_name")
    private String name;

	@Field("course_category")
    private String category;

    private Integer directionId;

    @Field("course_difficulty_level")
    private String difficultyLevel;

    @Field("course_learn_num")
    private Integer learnNum;
    
    @Field("course_small_pic")
    private String smallPic;

    @Field("course_add_time")
    private Date addTime;

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public Integer getDirectionId() {
        return directionId;
    }

    public void setDirectionId(Integer directionId) {
        this.directionId = directionId;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel == null ? null : difficultyLevel.trim();
    }

    public Integer getLearnNum() {
        return learnNum;
    }

    public void setLearnNum(Integer learnNum) {
        this.learnNum = learnNum;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic == null ? null : smallPic.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}