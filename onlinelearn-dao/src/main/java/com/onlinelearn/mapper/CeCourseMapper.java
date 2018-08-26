package com.onlinelearn.mapper;

import com.onlinelearn.pojo.CeCourse;
import com.onlinelearn.pojo.CeCourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CeCourseMapper {
    int countByExample(CeCourseExample example);

    int deleteByExample(CeCourseExample example);

    int deleteByPrimaryKey(Integer sourceId);

    int insert(CeCourse record);

    int insertSelective(CeCourse record);

    List<CeCourse> selectByExample(CeCourseExample example);

    CeCourse selectByPrimaryKey(Integer sourceId);

    int updateByExampleSelective(@Param("record") CeCourse record, @Param("example") CeCourseExample example);

    int updateByExample(@Param("record") CeCourse record, @Param("example") CeCourseExample example);

    int updateByPrimaryKeySelective(CeCourse record);

    int updateByPrimaryKey(CeCourse record);
}