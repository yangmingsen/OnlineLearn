package com.onlinelearn.mapper;

import com.onlinelearn.pojo.UrUser;
import com.onlinelearn.pojo.UrUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UrUserMapper {
    int countByExample(UrUserExample example);

    int deleteByExample(UrUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UrUser record);

    int insertSelective(UrUser record);

    List<UrUser> selectByExample(UrUserExample example);
    
    UrUser selectByUsername(String username);//新添加

    UrUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UrUser record, @Param("example") UrUserExample example);

    int updateByExample(@Param("record") UrUser record, @Param("example") UrUserExample example);

    int updateByPrimaryKeySelective(UrUser record);
   
    int updateByPrimaryKey(UrUser record);
    
    int updateByUsernameSelective(UrUser record);//新添加
    
    int updateByUsername(UrUser record);//新添加
    
}