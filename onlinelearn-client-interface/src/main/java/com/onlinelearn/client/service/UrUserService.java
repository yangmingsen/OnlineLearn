package com.onlinelearn.client.service;
import java.util.List;
import com.onlinelearn.pojo.UrUser;

import entity.PageResult;
import entity.Result;
import entity.User;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface UrUserService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<UrUser> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public Result add(UrUser user);
	
	
	/**
	 * 用户登录接口
	 * @param user
	 * @return
	 */
	public Result login(UrUser user);
	
	
	/**
	 * 更新用户信息
	 * 个人信息更新统一使用此接口
	 */
	public void update(UrUser user);
	

	/**
	 * 根据用户名（username）获取用户信息实体
	 * @param id
	 * @return
	 */
	public UrUser findOne(String username);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Integer [] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(UrUser user, int pageNum,int pageSize);
	
	
	/**
	 * 生成短信验证码
	 * @return
	 */
	public void createSmsCode(String phone);

	
	/**
	 * 判断短信验证码是否存在
	 * @param phone
	 * @return
	 */
	public boolean  checkSmsCode(String phone,String code);
	
}
