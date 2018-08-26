package com.onlinelearn.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CeCommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CeCommentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNull() {
            addCriterion("course_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNotNull() {
            addCriterion("course_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseIdEqualTo(Integer value) {
            addCriterion("course_id =", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotEqualTo(Integer value) {
            addCriterion("course_id <>", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThan(Integer value) {
            addCriterion("course_id >", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("course_id >=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThan(Integer value) {
            addCriterion("course_id <", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThanOrEqualTo(Integer value) {
            addCriterion("course_id <=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdIn(List<Integer> values) {
            addCriterion("course_id in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotIn(List<Integer> values) {
            addCriterion("course_id not in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdBetween(Integer value1, Integer value2) {
            addCriterion("course_id between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("course_id not between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andVideoIdIsNull() {
            addCriterion("video_id is null");
            return (Criteria) this;
        }

        public Criteria andVideoIdIsNotNull() {
            addCriterion("video_id is not null");
            return (Criteria) this;
        }

        public Criteria andVideoIdEqualTo(Integer value) {
            addCriterion("video_id =", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdNotEqualTo(Integer value) {
            addCriterion("video_id <>", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdGreaterThan(Integer value) {
            addCriterion("video_id >", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("video_id >=", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdLessThan(Integer value) {
            addCriterion("video_id <", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdLessThanOrEqualTo(Integer value) {
            addCriterion("video_id <=", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdIn(List<Integer> values) {
            addCriterion("video_id in", values, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdNotIn(List<Integer> values) {
            addCriterion("video_id not in", values, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdBetween(Integer value1, Integer value2) {
            addCriterion("video_id between", value1, value2, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("video_id not between", value1, value2, "videoId");
            return (Criteria) this;
        }

        public Criteria andCommenterIdIsNull() {
            addCriterion("commenter_id is null");
            return (Criteria) this;
        }

        public Criteria andCommenterIdIsNotNull() {
            addCriterion("commenter_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommenterIdEqualTo(String value) {
            addCriterion("commenter_id =", value, "commenterId");
            return (Criteria) this;
        }

        public Criteria andCommenterIdNotEqualTo(String value) {
            addCriterion("commenter_id <>", value, "commenterId");
            return (Criteria) this;
        }

        public Criteria andCommenterIdGreaterThan(String value) {
            addCriterion("commenter_id >", value, "commenterId");
            return (Criteria) this;
        }

        public Criteria andCommenterIdGreaterThanOrEqualTo(String value) {
            addCriterion("commenter_id >=", value, "commenterId");
            return (Criteria) this;
        }

        public Criteria andCommenterIdLessThan(String value) {
            addCriterion("commenter_id <", value, "commenterId");
            return (Criteria) this;
        }

        public Criteria andCommenterIdLessThanOrEqualTo(String value) {
            addCriterion("commenter_id <=", value, "commenterId");
            return (Criteria) this;
        }

        public Criteria andCommenterIdLike(String value) {
            addCriterion("commenter_id like", value, "commenterId");
            return (Criteria) this;
        }

        public Criteria andCommenterIdNotLike(String value) {
            addCriterion("commenter_id not like", value, "commenterId");
            return (Criteria) this;
        }

        public Criteria andCommenterIdIn(List<String> values) {
            addCriterion("commenter_id in", values, "commenterId");
            return (Criteria) this;
        }

        public Criteria andCommenterIdNotIn(List<String> values) {
            addCriterion("commenter_id not in", values, "commenterId");
            return (Criteria) this;
        }

        public Criteria andCommenterIdBetween(String value1, String value2) {
            addCriterion("commenter_id between", value1, value2, "commenterId");
            return (Criteria) this;
        }

        public Criteria andCommenterIdNotBetween(String value1, String value2) {
            addCriterion("commenter_id not between", value1, value2, "commenterId");
            return (Criteria) this;
        }

        public Criteria andComtentIsNull() {
            addCriterion("comtent is null");
            return (Criteria) this;
        }

        public Criteria andComtentIsNotNull() {
            addCriterion("comtent is not null");
            return (Criteria) this;
        }

        public Criteria andComtentEqualTo(String value) {
            addCriterion("comtent =", value, "comtent");
            return (Criteria) this;
        }

        public Criteria andComtentNotEqualTo(String value) {
            addCriterion("comtent <>", value, "comtent");
            return (Criteria) this;
        }

        public Criteria andComtentGreaterThan(String value) {
            addCriterion("comtent >", value, "comtent");
            return (Criteria) this;
        }

        public Criteria andComtentGreaterThanOrEqualTo(String value) {
            addCriterion("comtent >=", value, "comtent");
            return (Criteria) this;
        }

        public Criteria andComtentLessThan(String value) {
            addCriterion("comtent <", value, "comtent");
            return (Criteria) this;
        }

        public Criteria andComtentLessThanOrEqualTo(String value) {
            addCriterion("comtent <=", value, "comtent");
            return (Criteria) this;
        }

        public Criteria andComtentLike(String value) {
            addCriterion("comtent like", value, "comtent");
            return (Criteria) this;
        }

        public Criteria andComtentNotLike(String value) {
            addCriterion("comtent not like", value, "comtent");
            return (Criteria) this;
        }

        public Criteria andComtentIn(List<String> values) {
            addCriterion("comtent in", values, "comtent");
            return (Criteria) this;
        }

        public Criteria andComtentNotIn(List<String> values) {
            addCriterion("comtent not in", values, "comtent");
            return (Criteria) this;
        }

        public Criteria andComtentBetween(String value1, String value2) {
            addCriterion("comtent between", value1, value2, "comtent");
            return (Criteria) this;
        }

        public Criteria andComtentNotBetween(String value1, String value2) {
            addCriterion("comtent not between", value1, value2, "comtent");
            return (Criteria) this;
        }

        public Criteria andFavourNumIsNull() {
            addCriterion("favour_num is null");
            return (Criteria) this;
        }

        public Criteria andFavourNumIsNotNull() {
            addCriterion("favour_num is not null");
            return (Criteria) this;
        }

        public Criteria andFavourNumEqualTo(Integer value) {
            addCriterion("favour_num =", value, "favourNum");
            return (Criteria) this;
        }

        public Criteria andFavourNumNotEqualTo(Integer value) {
            addCriterion("favour_num <>", value, "favourNum");
            return (Criteria) this;
        }

        public Criteria andFavourNumGreaterThan(Integer value) {
            addCriterion("favour_num >", value, "favourNum");
            return (Criteria) this;
        }

        public Criteria andFavourNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("favour_num >=", value, "favourNum");
            return (Criteria) this;
        }

        public Criteria andFavourNumLessThan(Integer value) {
            addCriterion("favour_num <", value, "favourNum");
            return (Criteria) this;
        }

        public Criteria andFavourNumLessThanOrEqualTo(Integer value) {
            addCriterion("favour_num <=", value, "favourNum");
            return (Criteria) this;
        }

        public Criteria andFavourNumIn(List<Integer> values) {
            addCriterion("favour_num in", values, "favourNum");
            return (Criteria) this;
        }

        public Criteria andFavourNumNotIn(List<Integer> values) {
            addCriterion("favour_num not in", values, "favourNum");
            return (Criteria) this;
        }

        public Criteria andFavourNumBetween(Integer value1, Integer value2) {
            addCriterion("favour_num between", value1, value2, "favourNum");
            return (Criteria) this;
        }

        public Criteria andFavourNumNotBetween(Integer value1, Integer value2) {
            addCriterion("favour_num not between", value1, value2, "favourNum");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterion("time not between", value1, value2, "time");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}