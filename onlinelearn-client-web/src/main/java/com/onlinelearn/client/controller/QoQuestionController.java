package com.onlinelearn.client.controller;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.onlinelearn.pojo.QoQuestion;
import com.onlinelearn.pojo.QoReplier;
import com.onlinelearn.pojogroup.Detail;
import com.onlinelearn.client.service.QoQuestionService;
import com.onlinelearn.client.service.QoReplierService;

import entity.PageResult;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/question")
public class QoQuestionController {

	@Reference
	private QoQuestionService questionService;
	
	@Reference
	private QoReplierService replierService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<QoQuestion> findAll(){			
		return questionService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return questionService.findPage(page, rows);
	}
	
	/**
	 * 增加问题
	 * @param question
	 * @return
	 */
	@RequestMapping("/addQuestion")
	public Result addQuestion(@RequestBody QoQuestion question){
		
		//如果id存在或者用户id不存在则判断有误
		if(question.getId()!=null || question.getAskerId()==null || "".equals(question.getAskerId())) {
			return new Result(false, "增加失败1！！！"); 
		}
		
		try {
			questionService.add(question);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败2");
		}
	}
	
	
	/**
	 * 增加问题评论回答
	 * @param replier
	 * @return
	 */
	@RequestMapping("/addReplier")
	public Result addReplier(@RequestBody QoReplier replier){
		try {
			
			if(replier.getContent()==null ||  "".equals(replier.getContent())) {
				return new Result(false, "失败");
			}
			
			replierService.add(replier);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param question
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody QoQuestion question){
		try {
			questionService.update(question);
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
	public QoQuestion findOne(Integer id){
		return questionService.findOne(id);		
	}
	
	/**
	 * 前台通过传入问题id 
	 * 然后后台根据传入的id返回关于这个问题的所有问题问答评论信息
	 * @param id 传入的问题id
	 * @return
	 */
	@RequestMapping("/findByTopicId")
	public Detail findByTopicId(Integer id) {
		return replierService.findByTopicId(id);
	}
	
	/***
	 * 通过课程分类名称查找所有关于该分类的问题
	 * 例如 cery=JavaScript 表示查找关于JavaScript相关的所有问题
	 * @param cery 传入的问题分类
	 * @return
	 */
	@RequestMapping("/findAllQuestionByCategory")
	public List<QoQuestion>findAllQuestionByCategory(String cery) {
		if(cery == null || "".equals(cery)) {
			return null;
		}
		
		return questionService.findAllByCategory(cery);
	}
	
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Integer [] ids){
		try {
			questionService.delete(ids);
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
	public PageResult search(@RequestBody QoQuestion question, int page, int rows  ){
		return questionService.findPage(question, page, rows);		
	}
	
}
