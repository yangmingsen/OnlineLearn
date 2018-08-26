package com.onlinelearn.mapper;

import com.onlinelearn.pojo.CeComment;
import com.onlinelearn.pojo.CeCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CeCommentMapper {
    int countByExample(CeCommentExample example);

    int deleteByExample(CeCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CeComment record);

    int insertSelective(CeComment record);

    List<CeComment> selectByExample(CeCommentExample example);

    CeComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CeComment record, @Param("example") CeCommentExample example);

    int updateByExample(@Param("record") CeComment record, @Param("example") CeCommentExample example);

    int updateByPrimaryKeySelective(CeComment record);

    int updateByPrimaryKey(CeComment record);
}