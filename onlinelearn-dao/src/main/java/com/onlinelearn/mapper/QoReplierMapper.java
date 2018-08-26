package com.onlinelearn.mapper;

import com.onlinelearn.pojo.QoReplier;
import com.onlinelearn.pojo.QoReplierExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QoReplierMapper {
    int countByExample(QoReplierExample example);

    int deleteByExample(QoReplierExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(QoReplier record);

    int insertSelective(QoReplier record);

    List<QoReplier> selectByExample(QoReplierExample example);

    QoReplier selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") QoReplier record, @Param("example") QoReplierExample example);

    int updateByExample(@Param("record") QoReplier record, @Param("example") QoReplierExample example);

    int updateByPrimaryKeySelective(QoReplier record);

    int updateByPrimaryKey(QoReplier record);
}