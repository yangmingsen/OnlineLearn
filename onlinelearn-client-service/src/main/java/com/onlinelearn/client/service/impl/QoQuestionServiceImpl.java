package com.onlinelearn.client.service.impl;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onlinelearn.mapper.QoQuestionMapper;
import com.onlinelearn.pojo.QoQuestion;
import com.onlinelearn.pojo.QoQuestionExample;
import com.onlinelearn.pojo.QoQuestionExample.Criteria;
import com.onlinelearn.client.service.QoQuestionService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class QoQuestionServiceImpl implements QoQuestionService {

	@Autowired
	private QoQuestionMapper questionMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<QoQuestion> findAll() {
		return questionMapper.selectByExample(null);
	}
	
	public List<QoQuestion> findAllByUsername(String username) {
		QoQuestionExample qoQuestionExample = new QoQuestionExample();
		Criteria criteria = qoQuestionExample.createCriteria();
		criteria.andAskerIdEqualTo(username);
	
		return questionMapper.selectByExample(qoQuestionExample);
	}
	
	

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<QoQuestion> page=   (Page<QoQuestion>) questionMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(QoQuestion question) {
		
		//设置一些参数
		question.setAddTime(new Date());
		question.setStatus("0");
		question.setBrowseNum(0);
		
		questionMapper.insert(question);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(QoQuestion question){
		questionMapper.updateByPrimaryKey(question);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public QoQuestion findOne(Integer id){
		return questionMapper.selectByPrimaryKey(id);
	}
	
	/***
	 * 具体实现：
	 *  1.通过模糊查询关于该分类相关的所有数据
	 */
	public  List<QoQuestion> findAllByCategory(String cery) {
		
		//模糊查询
		QoQuestionExample qoQuestionExample = new QoQuestionExample();
		Criteria criteria = qoQuestionExample.createCriteria();
		criteria.andCategoryLike("%"+"\""+cery+"\""+"%");
		
		return questionMapper.selectByExample(qoQuestionExample);
		
	}
	

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			questionMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(QoQuestion question, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		QoQuestionExample example=new QoQuestionExample();
		Criteria criteria = example.createCriteria();
		
		if(question!=null){			
						if(question.getAskerId()!=null && question.getAskerId().length()>0){
				criteria.andAskerIdLike("%"+question.getAskerId()+"%");
			}
			if(question.getTitle()!=null && question.getTitle().length()>0){
				criteria.andTitleLike("%"+question.getTitle()+"%");
			}
			if(question.getContent()!=null && question.getContent().length()>0){
				criteria.andContentLike("%"+question.getContent()+"%");
			}
			if(question.getCategory()!=null && question.getCategory().length()>0){
				criteria.andCategoryLike("%"+question.getCategory()+"%");
			}
			if(question.getStatus()!=null && question.getStatus().length()>0){
				criteria.andStatusLike("%"+question.getStatus()+"%");
			}
	
		}
		
		Page<QoQuestion> page= (Page<QoQuestion>)questionMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
}
