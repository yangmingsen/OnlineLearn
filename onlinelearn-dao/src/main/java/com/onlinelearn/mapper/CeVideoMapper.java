package com.onlinelearn.mapper;

import com.onlinelearn.pojo.CeVideo;
import com.onlinelearn.pojo.CeVideoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CeVideoMapper {
    int countByExample(CeVideoExample example);

    int deleteByExample(CeVideoExample example);

    int deleteByPrimaryKey(Integer videoId);

    int insert(CeVideo record);

    int insertSelective(CeVideo record);

    List<CeVideo> selectByExample(CeVideoExample example);

    CeVideo selectByPrimaryKey(Integer videoId);

    int updateByExampleSelective(@Param("record") CeVideo record, @Param("example") CeVideoExample example);

    int updateByExample(@Param("record") CeVideo record, @Param("example") CeVideoExample example);

    int updateByPrimaryKeySelective(CeVideo record);

    int updateByPrimaryKey(CeVideo record);
}