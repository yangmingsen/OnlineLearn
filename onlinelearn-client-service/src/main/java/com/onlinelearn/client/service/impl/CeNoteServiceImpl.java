package com.onlinelearn.client.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.onlinelearn.client.service.CeNoteService;
import com.onlinelearn.mapper.CeCourseMapper;
import com.onlinelearn.mapper.CeNoteMapper;
import com.onlinelearn.mapper.CeVideoMapper;
import com.onlinelearn.mapper.UrUserMapper;
import com.onlinelearn.pojo.CeCourse;
import com.onlinelearn.pojo.CeNote;
import com.onlinelearn.pojo.CeNoteExample;
import com.onlinelearn.pojo.CeVideo;
import com.onlinelearn.pojo.CeNoteExample.Criteria;
import com.onlinelearn.pojo.UrUser;
import com.onlinelearn.pojogroup.CeNoteGroup;
import com.onlinelearn.pojogroup.UrCourseNoteHistory;

import entity.PageResult;

@Service
public class CeNoteServiceImpl implements CeNoteService {

	@Autowired//注入课程笔记mapper
	private CeNoteMapper noteMapper;
	
	@Autowired//注入用户mapper
	private UrUserMapper userMapper;

	@Autowired//注入视频mapper
	private CeVideoMapper videoMapper;
	
	@Autowired//注入课程mapper
	private CeCourseMapper courseMapper;
	
	@Override
	public List<CeNote> findAll() {
		return noteMapper.selectByExample(null);
	}
	
	/***
	 * 通过课程id和视频id查找所有 有关此课程对应视频的所有评论问答笔记信息
	 */
	public List<CeNoteGroup> findAllByCourseId(Integer cid, Integer vid) {
		
		//1.先通过课程cid查询所有关于该课程的笔记信息
		CeNoteExample ceNoteExample = new CeNoteExample();
		Criteria criteria =  ceNoteExample.createCriteria();
		criteria.andCourseIdEqualTo(cid);//添加查询条件为准确匹配课程id
		
		if(vid != null ) {//如果视频id不为空,表示进一步AND查询 
			criteria.andVideoIdEqualTo(vid);
		}
		
		List<CeNote> listNotes = noteMapper.selectByExample(ceNoteExample);
		
		List<CeNoteGroup> res = new ArrayList<CeNoteGroup>();
		//2.for循环查找每个笔记对应的用户,视频信息
		for(CeNote ceNote : listNotes) {
			
			//查找用户
			UrUser urUser = userMapper.selectByUsername(ceNote.getNoterId());
			urUser.setPassword("hamapi");
			
			//查找视频信息
			CeVideo ceVideo = videoMapper.selectByPrimaryKey(ceNote.getVideoId());
			
			res.add(new CeNoteGroup(urUser, ceVideo, ceNote));
			
		}
	 
		return res;
	}

	public List<UrCourseNoteHistory> findAllCourseNoteByUsername(String username) {
		//1.查询所有该username的笔记信息
		CeNoteExample ceNoteExample = new CeNoteExample();
		Criteria criteria = ceNoteExample.createCriteria();
		criteria.andNoterIdEqualTo(username);
		
		List<CeNote> notes = noteMapper.selectByExample(ceNoteExample);
		System.out.println("notes = "+notes.size());
		
		List<UrCourseNoteHistory> res = new ArrayList<UrCourseNoteHistory>();
		//2.根据CeNote中的Video_id和Course_id查找相应的信息
		for(CeNote note : notes) {
			//2.1 查找相应视频信息根据Vedio_id
			CeVideo video = videoMapper.selectByPrimaryKey(note.getVideoId());
			
			//2.2查找相应的课程信息根据Course_id
			CeCourse course = courseMapper.selectByPrimaryKey(note.getCourseId());
			
			res.add(new UrCourseNoteHistory(course, video, note));
			
		}
		
		return res;
		
	}
	
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(CeNote note) {

		note.setFavourNum(0);
		note.setTime(new Date());
		
		noteMapper.insert(note);

	}

	@Override
	public void update(CeNote note) {
		noteMapper.updateByPrimaryKey(note);
		
	}

	@Override
	public CeNote findOne(Integer noteId) {
		// TODO Auto-generated method stub
		return noteMapper.selectByPrimaryKey(noteId);
	}

	@Override
	public void delete(Integer[] noteIds) {
		// TODO Auto-generated method stub

	}

}
