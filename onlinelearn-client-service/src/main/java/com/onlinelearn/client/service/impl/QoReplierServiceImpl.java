package com.onlinelearn.client.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onlinelearn.mapper.QoQuestionMapper;
import com.onlinelearn.mapper.QoReplierMapper;
import com.onlinelearn.mapper.UrUserMapper;
import com.onlinelearn.pojo.QoQuestion;
import com.onlinelearn.pojo.QoReplier;
import com.onlinelearn.pojo.QoReplierExample;
import com.onlinelearn.pojo.QoReplierExample.Criteria;
import com.onlinelearn.pojo.UrUser;
import com.onlinelearn.pojogroup.Detail;
import com.onlinelearn.pojogroup.PersonReplier;
import com.onlinelearn.client.service.QoReplierService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class QoReplierServiceImpl implements QoReplierService {

	@Autowired
	private QoReplierMapper replierMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<QoReplier> findAll() {
		return replierMapper.selectByExample(null);
	}
	
	
	@Autowired//导入问题mapper方便查询问题
	private QoQuestionMapper qoQuestionMapper;
	
	@Autowired
	private UrUserMapper urUserMapper;
	/**
	 * 具体实现
	 */
	public Detail findByTopicId(Integer topicId) {
	
		//1.根据问题topicId找到关于这个问题相关详细信息
		QoQuestion qoQuestion = qoQuestionMapper.selectByPrimaryKey(topicId);
		//2.根据qoQuestion的AskerId找到提问人信息
		UrUser askerUserInfo = urUserMapper.selectByUsername(qoQuestion.getAskerId());
		askerUserInfo.setPassword("hamapi");
		
		//3.根据topicId找到所有关于这个问题的评论信息
		QoReplierExample qoReplierExample  = new QoReplierExample();
		Criteria criteria =qoReplierExample.createCriteria();
		
		if(topicId != null ) {
			criteria.andTopicIdEqualTo(topicId);
		}
		List<QoReplier> repliers = replierMapper.selectByExample(qoReplierExample);
		
		//4.用一个组合实体类数组PersonReplier临时存储所有评论信息，为了方便for查找评论者信息
		List<PersonReplier>personRepliers = new ArrayList<PersonReplier>();
		for(QoReplier qoReplier : repliers) {
			//4.1根据qoReplier的FromUid查找评论者信息
			UrUser urUser = urUserMapper.selectByUsername(qoReplier.getFromUid());
			urUser.setPassword("hamapi");
			//4.2装入数组
			personRepliers.add(new PersonReplier(urUser, qoReplier));
			
		}
		
		return new Detail(qoQuestion, askerUserInfo,personRepliers);
		
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<QoReplier> page=   (Page<QoReplier>) replierMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(QoReplier replier) {
		
		replier.setTime(new Date());
		
		replierMapper.insert(replier);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(QoReplier replier){
		replierMapper.updateByPrimaryKey(replier);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public QoReplier findOne(Integer id){
		return replierMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			replierMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(QoReplier replier, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		QoReplierExample example=new QoReplierExample();
		Criteria criteria = example.createCriteria();
		
		if(replier!=null){			
						if(replier.getTopicType()!=null && replier.getTopicType().length()>0){
				criteria.andTopicTypeLike("%"+replier.getTopicType()+"%");
			}
			if(replier.getContent()!=null && replier.getContent().length()>0){
				criteria.andContentLike("%"+replier.getContent()+"%");
			}
			if(replier.getFromUid()!=null && replier.getFromUid().length()>0){
				criteria.andFromUidLike("%"+replier.getFromUid()+"%");
			}
			if(replier.getToUid()!=null && replier.getToUid().length()>0){
				criteria.andToUidLike("%"+replier.getToUid()+"%");
			}
	
		}
		
		Page<QoReplier> page= (Page<QoReplier>)replierMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
}
