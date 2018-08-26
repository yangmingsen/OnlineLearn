package com.onlinelearn.mapper;

import com.onlinelearn.pojo.CeChapter;
import com.onlinelearn.pojo.CeChapterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CeChapterMapper {
    int countByExample(CeChapterExample example);

    int deleteByExample(CeChapterExample example);

    int deleteByPrimaryKey(Integer chapterId);

    int insert(CeChapter record);

    int insertSelective(CeChapter record);

    List<CeChapter> selectByExample(CeChapterExample example);

    CeChapter selectByPrimaryKey(Integer chapterId);

    int updateByExampleSelective(@Param("record") CeChapter record, @Param("example") CeChapterExample example);

    int updateByExample(@Param("record") CeChapter record, @Param("example") CeChapterExample example);

    int updateByPrimaryKeySelective(CeChapter record);

    int updateByPrimaryKey(CeChapter record);
}