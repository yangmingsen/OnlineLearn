package com.onlinelearn.mapper;

import com.onlinelearn.pojo.CeQuestion;
import com.onlinelearn.pojo.CeQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CeQuestionMapper {
    int countByExample(CeQuestionExample example);

    int deleteByExample(CeQuestionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CeQuestion record);

    int insertSelective(CeQuestion record);

    List<CeQuestion> selectByExample(CeQuestionExample example);

    CeQuestion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CeQuestion record, @Param("example") CeQuestionExample example);

    int updateByExample(@Param("record") CeQuestion record, @Param("example") CeQuestionExample example);

    int updateByPrimaryKeySelective(CeQuestion record);

    int updateByPrimaryKey(CeQuestion record);
}