package com.onlinelearn.client.controller;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.onlinelearn.pojo.AeArticle;
import com.onlinelearn.pojo.QoQuestion;
import com.onlinelearn.pojo.UrUser;
import com.onlinelearn.pojogroup.LearnVideoHistory;
import com.onlinelearn.pojogroup.UrCourseNoteHistory;
import com.onlinelearn.client.service.AeArticleService;
import com.onlinelearn.client.service.CeNoteService;
import com.onlinelearn.client.service.QoQuestionService;
import com.onlinelearn.client.service.UrLearnCourseHistoryService;
import com.onlinelearn.client.service.UrUserService;

import entity.PageResult;
import entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import utils.Const;
import utils.NewIpHelper;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UrUserController {

	@Reference
	private UrUserService userService;
	
	@Reference
	private UrLearnCourseHistoryService learnCourseHistoryService;
	
	@Reference
	private QoQuestionService questionService;
	
	@Reference
	private CeNoteService noteService;
	
	@Reference
	private AeArticleService articleService; 
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<UrUser> findAll(){			
		return userService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return userService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param user
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody UrUser user, String code){
		
		
		//先判断用户名是否为空或者不是空格
		if(user == null ||("".equals(user.getUsername()))) {
			return new Result(false, "注册出错1");
		 }
		//校验验证码是不是正确
		boolean checkSmsCode = userService.checkSmsCode(user.getPhone(), code);
		if(!checkSmsCode) {
			return new Result(false, "验证码不正确");
		}
		
		return userService.add(user);

	}
	
	/***
	 * 发送短信验证码接口
	 * @param phone
	 * @return
	 */
	@RequestMapping("/sendSmsCode")
	public Result sendSmsCode(String phone) {
		try {
			userService.createSmsCode(phone);
			return new Result(false, "发送验证码成功");
		} catch (Exception e) {
			return new Result(false, "发送验证码失败");
		}
	}
	
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	@RequestMapping("/login")
	public Result login(@RequestBody UrUser user) {
		
		System.out.println("userId="+user.getUsername());
		
		//1.先判断用户名是否为空或者不是空格
		if(user == null ||("".equals(user.getUsername()))) {
			return new Result(false, "登录出错1");
		 }
		
		Result result = userService.login(user);
		if(result.isSuccess()) {
			
			//创建token令牌
            String jwtToken = Jwts.builder().setSubject(user.getUsername())
                   .setExpiration(new Date(Const.JWT_TOKEN_EXP))
                   .signWith(SignatureAlgorithm.HS256, Const.JWT_SECRET_KEY)
                   .compact();
			
            result.setMessage("Bearer "+jwtToken);
            
			return result;
		} else {
			return result;
		}
		
	}
	
	/**
	 * 修改
	 * @param user
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody UrUser user){
		try {
			userService.update(user);
			return new Result(true, "更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "更新失败");
		}
	}	
	
	@Value("${IpAddress}")
	private String IpAddress;//图片服务器测试专用ip地址
	
	/**
	 * 根据用户名(username)获取用户信息实体
	 * @param username
	 * @return
	 */
	@RequestMapping("/findOne")
	public UrUser findOne(String username){
		
		if(username==null || "".equals(username)) {
			return new UrUser();
		}
		
		UrUser tmp = userService.findOne(username);	
		
		tmp.setHeadPic(NewIpHelper.getNewIp(tmp.getHeadPic(), IpAddress));//更新ip 测试专用。部署去掉
		
		return tmp;
	}
	
	/**
	 * 查找该用户userId所有的视频学习记录
	 * @param username 传入的用户名(username)
	 * @return List<UrLearnCourseHistory>
	 */
	@RequestMapping("/findCourseHistoryAllByUsername")
	public List<LearnVideoHistory> findCourseHistoryAllByUsername(String username) {
		return learnCourseHistoryService.findAllByUsername(username);
	}
	
	/***
	 * Function: 通过username查找该用户所有的提问
	 * @param username 用户名
	 * @return List< QoQuestion > 为用户所有的提问记录
	 */
	@RequestMapping("/findAllQuestionByUsername")
	public List<QoQuestion> findAllQuestionByUsername(String username) {
		
		if(username == null || "".equals(username)) {
			return null;
		}
		
		return questionService.findAllByUsername(username);
	}
	
	
	/***
	 * 查询所有该用户username的课程笔记信息 
	 * @param username 用户名
	 * @return UrCourseNoteHistory
	 */
	@RequestMapping("/findAllCourseNoteByUsername")
	public List<UrCourseNoteHistory> findAllCourseNoteByUsername(String username) {
	
		if(username == null || "".equals(username)) {
			return null;
		}
		
		return noteService.findAllCourseNoteByUsername(username);
	}
	
	/***
	 * 通过用户名(username)查找所有该用户所写的文章
	 * 注意： 用户名不能为空  为null 则返回null
	 * @param username 用户名
	 * @return List<AeArticle>
	 */
	@RequestMapping("/findAllArticleByUsername")
	public List<AeArticle> findAllArticleByUsername(String username) {
		if(username == null || "".equals(username)) {
			return null;
		}
		
		return articleService.findAllArticleByUsername(username);
		
	}
	
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Integer [] ids){
		try {
			userService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param brand
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody UrUser user, int page, int rows  ){
		return userService.findPage(user, page, rows);		
	}
	
}
