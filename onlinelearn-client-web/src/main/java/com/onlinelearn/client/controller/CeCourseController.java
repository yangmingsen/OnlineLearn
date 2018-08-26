package com.onlinelearn.client.controller;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.onlinelearn.pojo.CeChapterExample;
import com.onlinelearn.pojo.CeComment;
import com.onlinelearn.pojo.CeCourse;
import com.onlinelearn.pojo.CeNote;
import com.onlinelearn.pojo.CeQuestion;
import com.onlinelearn.pojo.CeQuestionReplier;
import com.onlinelearn.pojo.CeVideo;
import com.onlinelearn.pojo.UrLearnCourseHistory;
import com.onlinelearn.pojogroup.CeCommentGroup;
import com.onlinelearn.pojogroup.CeNoteGroup;
import com.onlinelearn.pojogroup.CeQuestionDetailGruop;
import com.onlinelearn.pojogroup.CeQuestionGroup;
import com.onlinelearn.pojogroup.Course;
import com.onlinelearn.pojogroup.PlayVideoGroup;
import com.onlinelearn.client.service.CeChapterService;
import com.onlinelearn.client.service.CeCommentService;
import com.onlinelearn.client.service.CeCourseService;
import com.onlinelearn.client.service.CeNoteService;
import com.onlinelearn.client.service.CeQuestionReplierService;
import com.onlinelearn.client.service.CeQuestionService;
import com.onlinelearn.client.service.CeVideoService;
import com.onlinelearn.client.service.QoQuestionService;
import com.onlinelearn.client.service.UrLearnCourseHistoryService;

import entity.PageResult;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/course")
public class CeCourseController {
	
	@Reference//引用远程课程服务
	private CeCourseService courseService;
	
	@Reference
	private CeVideoService videoService;
	
	@Reference//引用远程课程问答服务
	private CeQuestionService questionService;
	
	@Reference//引用远程问答回复服务
	private CeQuestionReplierService questionReplierService; 
	
	@Reference//引入课程评论信息服务
	private CeCommentService commentService;
	
	@Reference//引入课程笔记信息
	private CeNoteService noteService;
	
	@Reference
	private UrLearnCourseHistoryService learnCourseHistory;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<CeCourse> findAll(){			
		return courseService.findAll();
	}
	
	
	/**
	 * 根据课程id和视频id查找所有该课程的问答信息
	 * 注意：其中课程id必填而视频id可选
	 * @param cid 课程id 
	 * @param vid 视频id
	 * @return
	 */
	@RequestMapping("/findAllQuestionById")
	public List<CeQuestionGroup> findAllQuestionById(Integer cid, Integer vid) {
		
		if(cid == null) {//如果课程id 为空
			return null;
		}
		
		return questionService.findAllByCourseId(cid,vid);
		
	}
	
	/***
	 * 通过问题qid 查找关于该问题详细信息
	 * 
	 * @param qid 问题id
	 * @return CeQuestionDetailGruop
	 */
	@RequestMapping("/findQuestionDetailById")
	public CeQuestionDetailGruop findQuestionDetailById(Integer qid) {
		return questionService.findQuestionDetailById(qid);
	}
	

	/***
	 * 根据课程id和视频id查找所有该课程的评论信息
	 * 注意：其中课程id必填而视频id可选
	 * @param cid 课程id 
	 * @param vid 视频id
	 * @return
	 */
	@RequestMapping("/findAllCommentById")
	public List<CeCommentGroup> findAllCommentById(Integer cid, Integer vid) {
		
		if(cid == null) {//如果课程id 为空
			return null;
		}
		
		return commentService.findAllByCourseId(cid,vid);
		
	}
	
	/***
	 * 根据课程id和视频id查找所有该课程的笔记信息
	 * 注意：其中课程id必填 而视频id可选
	 * @param cid 课程id 
	 * @param vid 视频id
	 */
	@RequestMapping("/findAllCeNoteById")
	public List<CeNoteGroup> findAllCeNoteById(Integer cid, Integer vid) {
		
		if(cid == null) {//如果课程id 为空
			return null;
		}
		
		return noteService.findAllByCourseId(cid, vid);
		
	}
	
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return courseService.findPage(page, rows);
	}
	
	/**
	 * 通过课程方向查找课程
	 * @param deir
	 * @return
	 */
	@RequestMapping("/findByDirection")
	public List<CeCourse> findByDirection(String deir) {
		return courseService.findByDirection(deir);
	}
	
	/**
	 * 分类查找
	 * @param cery
	 * @return
	 */
	@RequestMapping("/findByCategory")
	public List<CeCourse> findByCategory(String cery) {
		
		return courseService.findByCategory(cery);
		
	}
	
	/**
	 * 增加
	 * @param course
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody CeCourse course){
		try {
			courseService.add(course);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/***
	 * 增加课程问题问答接口
	 * 其中：
	 * {
	 * "courseId":1,//课程id 
	 * "title":"hello wolrd yms",//标题
	 * "content":"fastDFS  我的听说的",//内容
	 * "videoId":1, //视频id
	 * "askerId":"yms@qq.com" //提问人
	 *	}
	 * @param question
	 * @return
	 */
	@RequestMapping("/addCeCourseQuestion")
	public Result addCeCourseQuestion(@RequestBody CeQuestion question) {
		
		try {
			questionService.add(question);
			return new Result(true, "添加问题成功!!!");
		} catch (Exception e) {
			return new Result(false, "添加问题失败!!!");
		}

	}
	
	/***
	 * 增加课程问答回复接口
	 * 其中：
	 * {
		"topicId":1, //主题id => 也就是问题id
		"topicType":"1", //主题类型  如果是1 表示该回复针对父问题（没有toId）   如果是2 表示针对其他回复人（有toId）
		"fromId":"yms@qq.com", //回复人
		"toId":null, //回复目标人
		"content":"这是测试增加问题回复接口" //回复内容
		}
	 * @param replier
	 * @return
	 */
	@RequestMapping("/addCeCourseQuestionReplier")
	public Result addCeCourseQuestionReplier(@RequestBody CeQuestionReplier replier) {
		
		try {
			
			questionReplierService.add(replier);
			return new Result(true, "添加问题回复成功！！！");
		} catch (Exception e) {
			return new Result(false, "添加问题回复失败！！！");
		}
		
	}
	
	/***
	 * 增加课程评论接口
	 * {
	 *	"courseId":1, //课程id
	 *	"videoId":1, //视频id
	 *	"commenterId":"yms@qq.com", //评论人id(username)
	 *	"comtent":"hello world Java Scala Python C++ " //内容
	 *	}
	 * @param comment
	 * @return
	 */
	@RequestMapping("/addCeCourseComment")
	public Result addCeCourseComment(@RequestBody CeComment comment) {
		
		try {
			
			commentService.add(comment);
			return new Result(true, "添加课程评论信息成功！！！");
		} catch (Exception e) {
			return new Result(false, "添加课程评论信息失败！！！");
		}
		
	}
	
	/***
	 * 
	 * @param note
	 * @return
	 */
	@RequestMapping("/addCourseNote")
	public Result addCourseNote(@RequestBody CeNote note) {
		
		try {
			noteService.add(note);
			return new Result(true, "增加课程笔记成功!!!");
		} catch (Exception e) {
			return new Result(false, "增加课程笔记失败!!!");
		}
		
	}
	
	
	
	
	
	
	/**
	 * 修改
	 * @param course
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody CeCourse course){
		try {
			courseService.update(course);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public CeCourse findOne(Integer id){
		return courseService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Integer [] ids){
		try {
			courseService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	

	
	/**
	 * 学习课程
	 * @param id
	 * @return
	 */
	@RequestMapping("/learn")
	public Course learn(Integer id) {
		return courseService.learnCourse(id);
	}
	
	/***
	 * 根据传入是视频vid 返回关于该视频的所有问答，评论,笔记信息
	 * @param vid 视频id
	 * @return
	 */
	@RequestMapping("/video")
	public PlayVideoGroup videoView(String username, Integer vid) {
	
		
		Integer cid = courseService.getCourseId(vid);
		
		addUserLearnCourseHisttory(username, vid, cid);//调用私用方法执行添加学习历史记录
		
		//查找视频信息
		CeVideo video = videoService.findOne(vid);
		
		//查找课程问答信息
		List<CeQuestionGroup> listQuestions = questionService.findAllByCourseId(cid,vid);
		
		//课程评论信息
		List<CeCommentGroup> listComments = commentService.findAllByCourseId(cid,vid);
		
		//课程笔记信息
		List<CeNoteGroup> listNotes = noteService.findAllByCourseId(cid, vid);
		
		return new PlayVideoGroup(cid,video, listQuestions, listComments,listNotes);
		
	}
	
	private void addUserLearnCourseHisttory(String username, Integer vid, Integer cid) {
		
		//1.先判断是否已经学习课程
		if(learnCourseHistory.isCourseLearning(username, vid)) {//如果存在
			//查询信息
			UrLearnCourseHistory history = learnCourseHistory.findOneByUidAndVid(username, vid);
			//更新时间
			history.setTime(new Date());
			learnCourseHistory.update(history);
			
		} else {//1.2如果没有学习则添加新纪录
			 
			UrLearnCourseHistory newHistory = new UrLearnCourseHistory();
			newHistory.setUserId(username);
			newHistory.setVideoId(vid);
			newHistory.setSourceId(cid);
			newHistory.setLearnTotalTime(0);
			newHistory.setTime(new Date());
			
			learnCourseHistory.add(newHistory);
		}
		
		
	}
	
	

		
	
	
	/**
	 * 播放视频
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/playVideo")
	public @ResponseBody void playVideo(Integer id, HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		//1.先判断是否为null
		if(id == null) {
			return ;
		}
		//2.从查找视频信息
		CeVideo ceVideo = videoService.findOne(id);
		
		System.out.println("path = "+ceVideo.getPath());
		
		//3.读取视频信息
		File file = new File(ceVideo.getPath());
		FileInputStream in = new FileInputStream(file);
		ServletOutputStream out = response.getOutputStream();
		byte[] b = null;
		while(in.available() >0) {
			if(in.available()>10240) {
				b = new byte[10240];
			}else {
				b = new byte[in.available()];
			}
			in.read(b, 0, b.length);
			out.write(b, 0, b.length);
		}
		in.close();
		out.flush();
		out.close();
		
		
	}
	
	@RequestMapping("/search")
	public Map<String, Object> search(@RequestBody Map searchMap) {
		
		System.out.println("keywords = "+searchMap.get("keywords"));
		
		return courseService.search(searchMap);
	}
	
	
}
