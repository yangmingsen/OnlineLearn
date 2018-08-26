package com.onlinelearn.mapper;

import com.onlinelearn.pojo.AeArticle;
import com.onlinelearn.pojo.AeArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AeArticleMapper {
    int countByExample(AeArticleExample example);

    int deleteByExample(AeArticleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AeArticle record);

    int insertSelective(AeArticle record);

    List<AeArticle> selectByExample(AeArticleExample example);

    AeArticle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AeArticle record, @Param("example") AeArticleExample example);

    int updateByExample(@Param("record") AeArticle record, @Param("example") AeArticleExample example);

    int updateByPrimaryKeySelective(AeArticle record);

    int updateByPrimaryKey(AeArticle record);
}