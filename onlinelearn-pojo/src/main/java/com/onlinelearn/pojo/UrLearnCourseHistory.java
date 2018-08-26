package com.onlinelearn.pojo;

import java.io.Serializable;
import java.util.Date;

public class UrLearnCourseHistory implements Serializable{
    private Integer id;

    private Integer sourceId;

    private String userId;

    private Integer videoId;

    private Integer learnTotalTime;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getLearnTotalTime() {
        return learnTotalTime;
    }

    public void setLearnTotalTime(Integer learnTotalTime) {
        this.learnTotalTime = learnTotalTime;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}