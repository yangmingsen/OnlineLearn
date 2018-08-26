package com.onlinelearn.mapper;

import com.onlinelearn.pojo.CeNote;
import com.onlinelearn.pojo.CeNoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CeNoteMapper {
    int countByExample(CeNoteExample example);

    int deleteByExample(CeNoteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CeNote record);

    int insertSelective(CeNote record);

    List<CeNote> selectByExample(CeNoteExample example);

    CeNote selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CeNote record, @Param("example") CeNoteExample example);

    int updateByExample(@Param("record") CeNote record, @Param("example") CeNoteExample example);

    int updateByPrimaryKeySelective(CeNote record);

    int updateByPrimaryKey(CeNote record);
}