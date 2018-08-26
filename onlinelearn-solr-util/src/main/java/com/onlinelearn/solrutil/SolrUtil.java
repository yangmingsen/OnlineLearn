package com.onlinelearn.solrutil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Component;

import com.onlinelearn.mapper.CeCourseMapper;
import com.onlinelearn.pojo.CeCourse;
import com.onlinelearn.pojo.CeCourseExample;
import com.onlinelearn.pojo.CeCourseExample.Criteria;

@Component
public class SolrUtil {

	@Autowired
	private CeCourseMapper ceCourseMapper;
	
	@Autowired
	private SolrTemplate solrTemplate;
	
	
	public void importCeCourseData() {
		
		System.out.println("hellwolrd");
		
		List<CeCourse> courseList = ceCourseMapper.selectByExample(null);//查询所有课程
		
		System.out.println("开始导入课程数据到solr...");
		
		solrTemplate.saveBeans(courseList);
		solrTemplate.commit();
		
		System.out.println("导入课程数据到solr完毕....");
		
	}
	
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath*:spring/applicationContext*.xml");
		SolrUtil solrUtil = (SolrUtil) context.getBean("solrUtil");
		solrUtil.importCeCourseData();
	}
	
	
}
