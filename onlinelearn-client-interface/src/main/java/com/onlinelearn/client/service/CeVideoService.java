package com.onlinelearn.client.service;
import java.util.List;
import com.onlinelearn.pojo.CeVideo;

import entity.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface CeVideoService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<CeVideo> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(CeVideo video);
	
	
	/**
	 * 修改
	 */
	public void update(CeVideo video);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public CeVideo findOne(Integer id);
	
	
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
	public PageResult findPage(CeVideo video, int pageNum,int pageSize);
	
	
	
}
