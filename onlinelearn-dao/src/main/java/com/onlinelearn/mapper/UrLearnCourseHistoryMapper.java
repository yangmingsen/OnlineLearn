package com.onlinelearn.mapper;

import com.onlinelearn.pojo.UrLearnCourseHistory;
import com.onlinelearn.pojo.UrLearnCourseHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UrLearnCourseHistoryMapper {
    int countByExample(UrLearnCourseHistoryExample example);

    int deleteByExample(UrLearnCourseHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UrLearnCourseHistory record);

    int insertSelective(UrLearnCourseHistory record);

    List<UrLearnCourseHistory> selectByExample(UrLearnCourseHistoryExample example);
    
    /**
     * 新添加方法
     * 通过传入userId向数据库查找所有该userId的数据
     * @param userId String类型,用户名(username)
     * @return
     */
    List<UrLearnCourseHistory> selectByUserId(String userId);

    UrLearnCourseHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UrLearnCourseHistory record, @Param("example") UrLearnCourseHistoryExample example);

    int updateByExample(@Param("record") UrLearnCourseHistory record, @Param("example") UrLearnCourseHistoryExample example);

    int updateByPrimaryKeySelective(UrLearnCourseHistory record);

    int updateByPrimaryKey(UrLearnCourseHistory record);
}