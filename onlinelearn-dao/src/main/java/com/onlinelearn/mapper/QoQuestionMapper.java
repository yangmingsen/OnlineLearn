package com.onlinelearn.mapper;

import com.onlinelearn.pojo.QoQuestion;
import com.onlinelearn.pojo.QoQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QoQuestionMapper {
    int countByExample(QoQuestionExample example);

    int deleteByExample(QoQuestionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(QoQuestion record);

    int insertSelective(QoQuestion record);

    List<QoQuestion> selectByExample(QoQuestionExample example);

    QoQuestion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") QoQuestion record, @Param("example") QoQuestionExample example);

    int updateByExample(@Param("record") QoQuestion record, @Param("example") QoQuestionExample example);

    int updateByPrimaryKeySelective(QoQuestion record);

    int updateByPrimaryKey(QoQuestion record);
}