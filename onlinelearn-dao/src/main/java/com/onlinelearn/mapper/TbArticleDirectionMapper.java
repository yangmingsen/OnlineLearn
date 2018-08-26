package com.onlinelearn.mapper;

import com.onlinelearn.pojo.TbArticleDirection;
import com.onlinelearn.pojo.TbArticleDirectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbArticleDirectionMapper {
    int countByExample(TbArticleDirectionExample example);

    int deleteByExample(TbArticleDirectionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbArticleDirection record);

    int insertSelective(TbArticleDirection record);

    List<TbArticleDirection> selectByExample(TbArticleDirectionExample example);

    TbArticleDirection selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbArticleDirection record, @Param("example") TbArticleDirectionExample example);

    int updateByExample(@Param("record") TbArticleDirection record, @Param("example") TbArticleDirectionExample example);

    int updateByPrimaryKeySelective(TbArticleDirection record);

    int updateByPrimaryKey(TbArticleDirection record);
}