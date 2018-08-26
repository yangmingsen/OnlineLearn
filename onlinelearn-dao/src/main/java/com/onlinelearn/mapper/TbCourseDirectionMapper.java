package com.onlinelearn.mapper;

import com.onlinelearn.pojo.TbCourseDirection;
import com.onlinelearn.pojo.TbCourseDirectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCourseDirectionMapper {
    int countByExample(TbCourseDirectionExample example);

    int deleteByExample(TbCourseDirectionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbCourseDirection record);

    int insertSelective(TbCourseDirection record);

    List<TbCourseDirection> selectByExample(TbCourseDirectionExample example);

    TbCourseDirection selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbCourseDirection record, @Param("example") TbCourseDirectionExample example);

    int updateByExample(@Param("record") TbCourseDirection record, @Param("example") TbCourseDirectionExample example);

    int updateByPrimaryKeySelective(TbCourseDirection record);

    int updateByPrimaryKey(TbCourseDirection record);
}