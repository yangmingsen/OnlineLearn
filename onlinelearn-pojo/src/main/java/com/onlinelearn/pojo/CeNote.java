package com.onlinelearn.pojo;

import java.io.Serializable;
import java.util.Date;

public class CeNote implements Serializable{
    private Integer id;

    private String noterId;

    private Integer courseId;

    private Integer videoId;

    private String content;

    private Integer favourNum;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoterId() {
        return noterId;
    }

    public void setNoterId(String noterId) {
        this.noterId = noterId == null ? null : noterId.trim();
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getFavourNum() {
        return favourNum;
    }

    public void setFavourNum(Integer favourNum) {
        this.favourNum = favourNum;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}