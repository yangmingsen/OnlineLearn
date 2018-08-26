package com.onlinelearn.client.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onlinelearn.mapper.CeVideoMapper;
import com.onlinelearn.pojo.CeVideo;
import com.onlinelearn.pojo.CeVideoExample;
import com.onlinelearn.pojo.CeVideoExample.Criteria;
import com.onlinelearn.client.service.CeVideoService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class CeVideoServiceImpl implements CeVideoService {

	@Autowired
	private CeVideoMapper videoMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<CeVideo> findAll() {
		return videoMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<CeVideo> page=   (Page<CeVideo>) videoMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(CeVideo video) {
		videoMapper.insert(video);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(CeVideo video){
		videoMapper.updateByPrimaryKey(video);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public CeVideo findOne(Integer id){
		return videoMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			videoMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(CeVideo video, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		CeVideoExample example=new CeVideoExample();
		Criteria criteria = example.createCriteria();
		
		if(video!=null){			
						if(video.getName()!=null && video.getName().length()>0){
				criteria.andNameLike("%"+video.getName()+"%");
			}
			if(video.getPath()!=null && video.getPath().length()>0){
				criteria.andPathLike("%"+video.getPath()+"%");
			}
			if(video.getDescription()!=null && video.getDescription().length()>0){
				criteria.andDescriptionLike("%"+video.getDescription()+"%");
			}
	
		}
		
		Page<CeVideo> page= (Page<CeVideo>)videoMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
		
	
	
}
