package com.onlinelearn.mapper;

import com.onlinelearn.pojo.CeQuestionReplier;
import com.onlinelearn.pojo.CeQuestionReplierExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CeQuestionReplierMapper {
    int countByExample(CeQuestionReplierExample example);

    int deleteByExample(CeQuestionReplierExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CeQuestionReplier record);

    int insertSelective(CeQuestionReplier record);

    List<CeQuestionReplier> selectByExample(CeQuestionReplierExample example);

    CeQuestionReplier selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CeQuestionReplier record, @Param("example") CeQuestionReplierExample example);

    int updateByExample(@Param("record") CeQuestionReplier record, @Param("example") CeQuestionReplierExample example);

    int updateByPrimaryKeySelective(CeQuestionReplier record);

    int updateByPrimaryKey(CeQuestionReplier record);
}