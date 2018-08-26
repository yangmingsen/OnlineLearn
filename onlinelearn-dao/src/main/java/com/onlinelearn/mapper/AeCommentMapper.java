package com.onlinelearn.mapper;

import com.onlinelearn.pojo.AeComment;
import com.onlinelearn.pojo.AeCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AeCommentMapper {
    int countByExample(AeCommentExample example);

    int deleteByExample(AeCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AeComment record);

    int insertSelective(AeComment record);

    List<AeComment> selectByExample(AeCommentExample example);

    AeComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AeComment record, @Param("example") AeCommentExample example);

    int updateByExample(@Param("record") AeComment record, @Param("example") AeCommentExample example);

    int updateByPrimaryKeySelective(AeComment record);

    int updateByPrimaryKey(AeComment record);
}