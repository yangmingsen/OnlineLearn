package com.onlinelearn.client.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onlinelearn.mapper.CeCommentMapper;
import com.onlinelearn.mapper.CeVideoMapper;
import com.onlinelearn.mapper.UrUserMapper;
import com.onlinelearn.pojo.CeComment;
import com.onlinelearn.pojo.CeCommentExample;
import com.onlinelearn.pojo.CeVideo;
import com.onlinelearn.pojo.UrUser;
import com.onlinelearn.pojo.CeCommentExample.Criteria;
import com.onlinelearn.pojogroup.CeCommentGroup;
import com.onlinelearn.client.service.CeCommentService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class CeCommentServiceImpl implements CeCommentService {

	@Autowired
	private CeCommentMapper commentMapper;
	
	@Autowired//注入用户mapper
	private UrUserMapper userMapper;
	
	@Autowired//注入视频mapper
	private CeVideoMapper videoMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<CeComment> findAll() {
		return commentMapper.selectByExample(null);
	}
	
	
	public List<CeCommentGroup> findAllByCourseId(Integer cid, Integer vid) {
		
		//1.首先根据id查找所有该课程的评论信息
		CeCommentExample ceCommentExample = new CeCommentExample();
		Criteria criteria = ceCommentExample.createCriteria();
		criteria.andCourseIdEqualTo(cid);
		
		if(vid != null ) {//如果有视频id选项则增加AND 视频查询
			criteria.andVideoIdEqualTo(vid);
		}
		
		List<CeComment> listComments = commentMapper.selectByExample(ceCommentExample);
		
		List<CeCommentGroup> res = new ArrayList<CeCommentGroup>();
		//2.for循环查找每个评论对应的用户,视频信息
		for(CeComment ceComment : listComments) {
			
			//根据用户id查找用户信息
			UrUser urUser = userMapper.selectByUsername(ceComment.getCommenterId());
			urUser.setPassword("hamapi");
			
			//根据视频id查找视频信息
			CeVideo ceVideo = videoMapper.selectByPrimaryKey(ceComment.getVideoId());
			
			res.add(new CeCommentGroup(urUser, ceComment, ceVideo));
			
		}
		
		return res;
		
	}
	

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<CeComment> page=   (Page<CeComment>) commentMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(CeComment comment) {
		
		comment.setFavourNum(0);
		comment.setTime(new Date());
		
		commentMapper.insert(comment);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(CeComment comment){
		commentMapper.updateByPrimaryKey(comment);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public CeComment findOne(Integer id){
		return commentMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			commentMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
	
	
}
