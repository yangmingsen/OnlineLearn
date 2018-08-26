package com.onlinelearn.client.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onlinelearn.mapper.CeQuestionMapper;
import com.onlinelearn.mapper.CeQuestionReplierMapper;
import com.onlinelearn.mapper.CeVideoMapper;
import com.onlinelearn.mapper.UrUserMapper;
import com.onlinelearn.pojo.CeQuestion;
import com.onlinelearn.pojo.CeQuestionExample;
import com.onlinelearn.pojo.CeQuestionExample.Criteria;
import com.onlinelearn.pojo.CeQuestionReplier;
import com.onlinelearn.pojo.CeQuestionReplierExample;
import com.onlinelearn.pojo.CeVideo;
import com.onlinelearn.pojo.UrUser;
import com.onlinelearn.pojogroup.CeQuestionDetailGruop;
import com.onlinelearn.pojogroup.CeQuestionGroup;
import com.onlinelearn.client.service.CeQuestionService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class CeQuestionServiceImpl implements CeQuestionService {

	@Autowired
	private CeQuestionMapper questionMapper;
	
	@Autowired//注入课程问题回复mapper
	private CeQuestionReplierMapper questionReplierMapper;
	
	@Autowired//注入用户mapper
	private UrUserMapper userMapper;
	
	@Autowired
	private CeVideoMapper videoMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<CeQuestion> findAll() {
		return questionMapper.selectByExample(null);
	}
	
	public List<CeQuestionGroup> findAllByCourseId(Integer cid, Integer vid) {
		
		//1.先根据课程id找到所有该课程问题
		CeQuestionExample ceQuestionExample = new CeQuestionExample();
		Criteria criteria = ceQuestionExample.createCriteria();
		criteria.andCourseIdEqualTo(cid);//根据id查找条件
	
		if(vid != null ) {
			criteria.andVideoIdEqualTo(vid);
		}
		
		List<CeQuestion> listQuestions = questionMapper.selectByExample(ceQuestionExample);
		
		List<CeQuestionGroup> res = new ArrayList<CeQuestionGroup>();
		
		//2.for循环查找每个问题对应的回答,视频,提问用户
		for(CeQuestion ceQuestion : listQuestions ) {
			
			//查询用户信息根据(username)
			UrUser urUser = userMapper.selectByUsername(ceQuestion.getAskerId());
			urUser.setPassword("hamapi");
			
			//查询视频信息根据(id)
			CeVideo ceVideo = videoMapper.selectByPrimaryKey(ceQuestion.getVideoId());
			
			//根据提问id来统计回答数
			CeQuestionReplierExample ceQuestionReplierExample = new CeQuestionReplierExample();
			com.onlinelearn.pojo.CeQuestionReplierExample.Criteria criteria2 = ceQuestionReplierExample.createCriteria();
			criteria2.andTopicIdEqualTo(ceQuestion.getId());
			
			int countRepliers = questionReplierMapper.countByExample(ceQuestionReplierExample);
			
			res.add(new CeQuestionGroup(urUser,ceQuestion,ceVideo,countRepliers));
			
		}
		
		return res;	
	}

	
	public CeQuestionDetailGruop findQuestionDetailById(Integer qid) {
		//1.通过id查找该问题详细数据
		CeQuestion question = questionMapper.selectByPrimaryKey(qid);
		
		//2.再通过username查找该提问者详细信息
		UrUser user = userMapper.selectByUsername(question.getAskerId());
		
		//3.通过问题id查找对于该问题的所有评论信息
		CeQuestionReplierExample ceQuestionReplierExample = new CeQuestionReplierExample();
		com.onlinelearn.pojo.CeQuestionReplierExample.Criteria criteria = ceQuestionReplierExample.createCriteria();
		criteria.andTopicIdEqualTo(question.getId());
		List<CeQuestionReplier> repilers = questionReplierMapper.selectByExample(ceQuestionReplierExample);
		
		//查找Video名字
		CeVideo video = videoMapper.selectByPrimaryKey(question.getVideoId());
		
		return new CeQuestionDetailGruop(video.getName(),user, question, repilers);
		
	}
	
	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<CeQuestion> page=   (Page<CeQuestion>) questionMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(CeQuestion question) {
		
		question.setBrowseNum(0);
		question.setStatus("0");
		question.setAddTime(new Date());
		
		questionMapper.insert(question);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(CeQuestion question){
		questionMapper.updateByPrimaryKey(question);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public CeQuestion findOne(Integer id){
		return questionMapper.selectByPrimaryKey(id);
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
	

	
}
