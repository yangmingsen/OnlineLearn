package com.onlinelearn.client.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mysql.fabric.xmlrpc.base.Array;
import com.onlinelearn.client.service.UrLearnCourseHistoryService;
import com.onlinelearn.mapper.CeCourseMapper;
import com.onlinelearn.mapper.CeVideoMapper;
import com.onlinelearn.mapper.UrLearnCourseHistoryMapper;
import com.onlinelearn.pojo.CeCourse;
import com.onlinelearn.pojo.CeVideo;
import com.onlinelearn.pojo.UrLearnCourseHistory;
import com.onlinelearn.pojo.UrLearnCourseHistoryExample;
import com.onlinelearn.pojo.UrLearnCourseHistoryExample.Criteria;
import com.onlinelearn.pojogroup.LearnVideoHistory;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class UrLearnCourseHistoryServiceImpl implements UrLearnCourseHistoryService {

	@Autowired
	private UrLearnCourseHistoryMapper learnCourseHistoryMapper;
	
	
	/**
	 * 查询全部
	 */
	@Override
	public List<UrLearnCourseHistory> findAll() {
		return learnCourseHistoryMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<UrLearnCourseHistory> page=   (Page<UrLearnCourseHistory>) learnCourseHistoryMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(UrLearnCourseHistory learnCourseHistory) {
		learnCourseHistoryMapper.insert(learnCourseHistory);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(UrLearnCourseHistory learnCourseHistory){
		learnCourseHistoryMapper.updateByPrimaryKey(learnCourseHistory);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public UrLearnCourseHistory findOne(Integer id){
		return learnCourseHistoryMapper.selectByPrimaryKey(id);
	}
	
	

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer [] ids) {
		for(Integer id:ids){
			learnCourseHistoryMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(UrLearnCourseHistory learnCourseHistory, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		UrLearnCourseHistoryExample example=new UrLearnCourseHistoryExample();
		Criteria criteria = example.createCriteria();
		
		if(learnCourseHistory!=null){			
				if(learnCourseHistory.getUserId()!=null && learnCourseHistory.getUserId().length()>0){
				criteria.andUserIdLike("%"+learnCourseHistory.getUserId()+"%");
			}
	
		}
		
		Page<UrLearnCourseHistory> page= (Page<UrLearnCourseHistory>)learnCourseHistoryMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

		@Autowired//注入ceCourseMapper 便于查找课程信息
		private CeCourseMapper ceCourseMapper;
		
		@Autowired//注入ceVideoMapper 便于查找视频学习
		private CeVideoMapper ceVideoMapper;
		
		/**
		 *具体实现:先通过历史记录表拿到所有历史记录，然后根据
		 *历史记录的id值分别找到对应的课程和视频信息，然后返回
		 */
		public List<LearnVideoHistory> findAllByUsername(String userId) {
		
			//1.查找所有该用户usrId历史记录从数据库中
			List<UrLearnCourseHistory> urLearnCourseHistories = learnCourseHistoryMapper.selectByUserId(userId);
			
			//返回值
			List<LearnVideoHistory> list = new ArrayList<LearnVideoHistory>();
			
			//2.for循环每个历史记录，将用户学习过的课程信息
			//和视频信息全部找出来，然后打包装入UrLearnCourseHistory,并返回List<UrLearnCourseHistory>
			for(UrLearnCourseHistory urLearnCourseHistory : urLearnCourseHistories) {
				
				//2.1拿到课程信息
				CeCourse ceCourse = ceCourseMapper.selectByPrimaryKey(urLearnCourseHistory.getSourceId());
				
				//2.2拿到视频信息
				CeVideo ceVideo = ceVideoMapper.selectByPrimaryKey(urLearnCourseHistory.getVideoId());
				
				//3.装入组合实体LearnVideoHistory 中
				list.add(new LearnVideoHistory(urLearnCourseHistory, ceCourse, ceVideo));
				
			}
			
			return list;
			
			
		}
	
		
		public boolean isCourseLearning(String username,Integer vid) {
			
			UrLearnCourseHistory ok = this.findOneByUidAndVid(username, vid);
		
			if(ok != null) {
				return true;//存在
			}
			
			return false;
		}
		
		public UrLearnCourseHistory findOneByUidAndVid(String username,Integer vid) {
			
			UrLearnCourseHistoryExample urLearnCourseHistoryExample = new UrLearnCourseHistoryExample();
			Criteria criteria = urLearnCourseHistoryExample.createCriteria();
			criteria.andUserIdEqualTo(username);
			criteria.andVideoIdEqualTo(vid);
			List<UrLearnCourseHistory> has = learnCourseHistoryMapper.selectByExample(urLearnCourseHistoryExample);
			
			if(has == null || has.size()==0) {
				return null;
			}
			
			System.out.println("size = "+has.size());
			
			return has.get(0);
		}	
		
		
		
		
}
