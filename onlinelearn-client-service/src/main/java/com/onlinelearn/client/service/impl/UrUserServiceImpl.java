package com.onlinelearn.client.service.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.zookeeper.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onlinelearn.mapper.UrUserMapper;
import com.onlinelearn.pojo.UrUser;
import com.onlinelearn.pojo.UrUserExample;
import com.onlinelearn.pojo.UrUserExample.Criteria;
import com.onlinelearn.client.service.UrUserService;

import entity.PageResult;
import entity.User;
import entity.Result;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class UrUserServiceImpl implements UrUserService {

	@Autowired
	private UrUserMapper userMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<UrUser> findAll() {
		return userMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<UrUser> page=   (Page<UrUser>) userMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	//测试使用用户默认头像图片
	private String headPic = "http://10.0.0.21:1234/group1/M00/00/00/wKgZhVtRinmALgWmAADh9UPPz0I366.jpg";
	
	/***
	 * 增加一个用户
	 * @param user 新用户
	 */
	public Result add(UrUser user) {
		
		//1.向数据库查找该用户信息
		UrUser urUser = userMapper.selectByUsername(user.getUsername());
		if(urUser!=null) {
			return new Result(false, "该用户已存在");
		}
		
		//2.加密用户密码
		String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(password);
		
		//3.设置默认头像
		user.setHeadPic(headPic);
		
		
		try {//4.若不存在则添加用户到数据库中然后返回注册成功信息
		
		    userMapper.insert(user);
		
			return new Result(true, "注册成功");
		} catch(Exception e) {
			return new Result(false, "注册出错2");
		}
		
	}
	
	
	@Autowired//注入redis方便存储验证码
	private RedisTemplate redisTemplate;
	
	@Autowired//注入方便短信发送
	private Destination smsDestination;
	
	@Value("${template_code}")//模板编号
	private String template_code;
	
	@Value("${sign_name}")//签名
	private String sign_name;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	private final static String authCode = "smsCode";//短信验证码bigKey存入redis的大键
	
	/**
	 * 创建验证码
	 * @param phone
	 */
	public void createSmsCode(final String phone) {
		final String code = (long)(Math.random()*1000000)+"";//随机生成6位数字
		System.out.println("验证码为: "+code);
		redisTemplate.boundHashOps(authCode).put(phone, code);//使用Redis保存生成的code
		
		
		
		//发送到activeMQ	....
		jmsTemplate.send(smsDestination, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				MapMessage mapMessage = session.createMapMessage();
				mapMessage.setString("mobile", phone);
				mapMessage.setString("template_code", template_code);
				mapMessage.setString("sign_name", sign_name);
				
				Map map = new HashMap<>();
				map.put("code", code);
				
				mapMessage.setString("param", JSON.toJSONString(map));
				
				return mapMessage;
			}
		});
	}
	
	/***
	 * 校验验证码
	 * @param phone
	 * @param code
	 * @return
	 */
	public boolean checkSmsCode(String phone, String code) {
		//从缓存中拿到phone对应的code
		String authcode = (String) redisTemplate.boundHashOps(authCode).get(phone);
		if(authcode == null ) {
			return false;
		}
		
		if(!authcode.equals(code)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public Result login(UrUser user) {
		
		//1.查询用户信息
		UrUser urUser = userMapper.selectByUsername(user.getUsername());
		if(urUser == null) {
			return new Result(false, "用户不存在");
		}
		
		//2.判断用户密码是否正确(新密码,原来密码)
		boolean isOk = BCrypt.checkpw(user.getPassword(),urUser.getPassword());
		if(!isOk) {
			return new Result(false, "密码不正确");
		}
		
		//3.更新登录时间
		urUser.setLastLoginTime(new Date());
		userMapper.updateByPrimaryKey(urUser);
		
		return new Result(true, "密码正确");
		
	}

	
	/**
	 * 这是统一更新方法实现:
	 * 
	 */
	@Override
	public void update(UrUser user){
		userMapper.updateByUsernameSelective(user);
	}	
	
	/**
	 * 根据用户名(username)获取实体
	 * @param id
	 * @return
	 */
	@Override
	public UrUser findOne(String username){
		
		UrUser urUser = userMapper.selectByUsername(username);
		if(urUser==null) {
			return new UrUser();
		}
		urUser.setPassword("hamapi");//密码破坏
		
		return urUser;
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			userMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(UrUser user, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		UrUserExample example=new UrUserExample();
		Criteria criteria = example.createCriteria();
		
		if(user!=null){			
						if(user.getUsername()!=null && user.getUsername().length()>0){
				criteria.andUsernameLike("%"+user.getUsername()+"%");
			}
			if(user.getPassword()!=null && user.getPassword().length()>0){
				criteria.andPasswordLike("%"+user.getPassword()+"%");
			}
			if(user.getPhone()!=null && user.getPhone().length()>0){
				criteria.andPhoneLike("%"+user.getPhone()+"%");
			}
			if(user.getEmail()!=null && user.getEmail().length()>0){
				criteria.andEmailLike("%"+user.getEmail()+"%");
			}
			if(user.getSex()!=null && user.getSex().length()>0){
				criteria.andSexLike("%"+user.getSex()+"%");
			}
			if(user.getHeadPic()!=null && user.getHeadPic().length()>0){
				criteria.andHeadPicLike("%"+user.getHeadPic()+"%");
			}
			if(user.getPersonalizedSignature()!=null && user.getPersonalizedSignature().length()>0){
				criteria.andPersonalizedSignatureLike("%"+user.getPersonalizedSignature()+"%");
			}
			if(user.getIsEmailCheck()!=null && user.getIsEmailCheck().length()>0){
				criteria.andIsEmailCheckLike("%"+user.getIsEmailCheck()+"%");
			}
	
		}
		
		Page<UrUser> page= (Page<UrUser>)userMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

	
	
}
