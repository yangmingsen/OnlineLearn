package com.onlinelearn.client.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onlinelearn.mapper.CeChapterMapper;
import com.onlinelearn.pojo.CeChapter;
import com.onlinelearn.pojo.CeChapterExample;
import com.onlinelearn.pojo.CeChapterExample.Criteria;
import com.onlinelearn.client.service.CeChapterService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class CeChapterServiceImpl implements CeChapterService {

	@Autowired
	private CeChapterMapper chapterMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<CeChapter> findAll() {
		return chapterMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<CeChapter> page=   (Page<CeChapter>) chapterMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(CeChapter chapter) {
		chapterMapper.insert(chapter);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(CeChapter chapter){
		chapterMapper.updateByPrimaryKey(chapter);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public CeChapter findOne(Integer chapterId){
		return chapterMapper.selectByPrimaryKey(chapterId);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] chapterIds) {
		for(Integer chapterId:chapterIds){
			chapterMapper.deleteByPrimaryKey(chapterId);
		}		
	}
	
	
		@Override
	public PageResult findPage(CeChapter chapter, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		CeChapterExample example=new CeChapterExample();
		Criteria criteria = example.createCriteria();
		
		if(chapter!=null){			
						if(chapter.getDescription()!=null && chapter.getDescription().length()>0){
				criteria.andDescriptionLike("%"+chapter.getDescription()+"%");
			}
			if(chapter.getName()!=null && chapter.getName().length()>0){
				criteria.andNameLike("%"+chapter.getName()+"%");
			}
	
		}
		
		Page<CeChapter> page= (Page<CeChapter>)chapterMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
}
