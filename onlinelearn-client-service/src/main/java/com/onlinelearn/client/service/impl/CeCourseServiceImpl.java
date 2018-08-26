package com.onlinelearn.client.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.HighlightQuery;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SimpleStringCriteria;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightEntry.Highlight;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.core.query.result.ScoredPage;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onlinelearn.mapper.CeChapterMapper;
import com.onlinelearn.mapper.CeCourseMapper;
import com.onlinelearn.mapper.CeVideoMapper;
import com.onlinelearn.mapper.TbCourseDirectionMapper;
import com.onlinelearn.pojo.CeChapter;
import com.onlinelearn.pojo.CeChapterExample;
import com.onlinelearn.pojo.CeCourse;
import com.onlinelearn.pojo.CeCourseExample;
import com.onlinelearn.pojo.CeCourseExample.Criteria;
import com.onlinelearn.pojo.CeVideo;
import com.onlinelearn.pojo.CeVideoExample;
import com.onlinelearn.pojo.TbArticleDirection;
import com.onlinelearn.pojo.TbCourseDirection;
import com.onlinelearn.pojo.TbCourseDirectionExample;
import com.onlinelearn.pojogroup.Chapter;
import com.onlinelearn.pojogroup.Course;
import com.onlinelearn.client.service.CeCourseService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service(timeout=6000)
public class CeCourseServiceImpl implements CeCourseService {

	@Autowired //注入课程mapper
	private CeCourseMapper courseMapper;
	
	@Autowired//注入章节mapper
	private CeChapterMapper chapterMapper;
	
	@Autowired//注入章节视频mapper
	private CeVideoMapper videoMapper;
	
	@Autowired//注入redis  缓存课程,{bigKey=course; smalKey=category,direction_id ;value=list<CeCourse>}
	private RedisTemplate redisTemplate;
	
	private final static String bigKey = "course";
	
	/**
	 * 查询全部课程
	 */
	@Override
	public List<CeCourse> findAll() {
		return courseMapper.selectByExample(null);
	}

	/**
	 * 按分页查询课程
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<CeCourse> page=   (Page<CeCourse>) courseMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}
	
	
	@Autowired
	private TbCourseDirectionMapper tbCourseDirectionMapper;
	/**
	 * 通过方向来查询课程,定义Criteria
	 * 然后通过精确查询方向进行查询课程。
	 */
	@Override
	public List<CeCourse> findByDirection(String deir) {

		//首先从redis中查找数据
		List<CeCourse> res = (List<CeCourse>) redisTemplate.boundHashOps(bigKey).get(deir);
		
		if(res == null ) {//从数据库中查找
			//1.先查找课程方向对应的课程方向id，然后根据方向id查找相应方向课程
			TbCourseDirectionExample tbCourseDirectionExample = new TbCourseDirectionExample();
			com.onlinelearn.pojo.TbCourseDirectionExample.Criteria criteria2 = tbCourseDirectionExample.createCriteria();
			if( (deir!=null) && (!("".equals(deir))) ) {
				criteria2.andNameEqualTo(deir);
			}
			 List<TbCourseDirection> tbArticleDirection = tbCourseDirectionMapper.selectByExample(tbCourseDirectionExample);
	
			 if(tbArticleDirection.size() == 0) {//如果该课程方向不存在
				 return null;
			 }
			
			//2.根据课程方向id查找该方向所有课程
			 CeCourseExample ceCourseExample = new CeCourseExample();
			 Criteria criteria = ceCourseExample.createCriteria();
			 criteria.andDirectionIdEqualTo(tbArticleDirection.get(0).getId());
			 
			 res = courseMapper.selectByExample(ceCourseExample);
			 
			 redisTemplate.boundHashOps(bigKey).put(deir, res);//将数据库中的数据添加到redis中
			 
			 System.out.println("方式：方向查找; 信息:从数据库中读取"+res.size()+"条"+"CeCourse数据");
		} else {
			System.out.println("方式：方向查找; 信息:从Redis中读取"+res.size()+"条"+"CeCourse数据");
		}
		
		return res ;
	}
	
	/**
	 * 根据课程分类查找课程
	 */
	public List<CeCourse> findByCategory(String category) {
		
		List<CeCourse> res = (List<CeCourse>)redisTemplate.boundHashOps(bigKey).get(category);
		
		if(res == null) {
			
			//根据课程分类category 字段 模糊查找课程
			CeCourseExample ceCourseExample = new CeCourseExample();
			Criteria criteria = ceCourseExample.createCriteria();
			criteria.andCategoryLike("%"+category+"%");
			
			res =  courseMapper.selectByExample(ceCourseExample);
			
			redisTemplate.boundHashOps(bigKey).put(category, res);
			
			System.out.println("方式：分类查找; 信息:从数据库中读取"+res.size()+"条"+"CeCourse数据");
		} else {
			System.out.println("方式：分类查找; 信息:从Redis中读取"+res.size()+"条"+"CeCourse数据");
		}
		
		return res;
		
	}


	/**
	 * 增加
	 */
	@Override
	public void add(CeCourse course) {
		courseMapper.insert(course);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(CeCourse course){
		courseMapper.updateByPrimaryKey(course);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public CeCourse findOne(Integer id){
		return courseMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			courseMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
	/**
	 * 通过课程id查询章节id然后根据章节id查询相应视频
	 * 课程对应多个章节,而每个章节又对应多个知识点(视频)
	 * 先查章节=>然后根据查找到的章节id列表=>通过for
	 * 循环查找所有章节相应视频
	 * 最后返回组合实体类
	 */
	@Override
	public Course learnCourse(Integer id) {
		
		// TODO Auto-generated method stub
		//1.根据课程id查找章节
		CeChapterExample ceChapterExample = new CeChapterExample();
		com.onlinelearn.pojo.CeChapterExample.Criteria criteria1 = ceChapterExample.createCriteria();
		if(id != null ) {
			criteria1.andSourceIdEqualTo(id);
		}
		List<CeChapter> ceChapters = chapterMapper.selectByExample(ceChapterExample);//使用一个List接受所有章节
		
		List<Chapter> chapters = new ArrayList<Chapter>();//new 一个List<Chapter>组合实体类包含一个课程所有信息
		
		List<CeVideo> ceVideos = new ArrayList<CeVideo>();//使用一个
		//3.for循环所有章节id,然后根据每个章节id查找相应视频
		for(CeChapter chapter : ceChapters) {
			
			CeVideoExample ceVideoExample = new CeVideoExample();
			com.onlinelearn.pojo.CeVideoExample.Criteria criteria2 = ceVideoExample.createCriteria();
			criteria2.andChapterIdEqualTo(chapter.getChapterId());//精确匹配id
			ceVideoExample.setOrderByClause("sort_order");//排序
			//将组合实体类Chapter添加到List中
			chapters.add(new Chapter(chapter, videoMapper.selectByExample(ceVideoExample)));

			 ceVideoExample = null;
		}
		//4.返回组合实体Course{CeCourse{},List<Chapter{},List<Video{}>}
		return new Course(courseMapper.selectByPrimaryKey(id), chapters);
	}
	

	@Autowired
	private SolrTemplate solrTemplate;
	
	/***
	 * 根据关键字去solr中搜索
	 */
	public Map<String, Object> search(Map searchMap) {
		
		//1.关键字空格处理
		String keywords = (String)searchMap.get("keywords");
		searchMap.put("keywords", keywords.replace(" ", ""));
		
		
		Map<String, Object> res = new HashMap<>();
		
		//查询
		res.putAll(searchListTwo(searchMap));
		
		
		return res;
		
	}
	
	private Map<String, Object> searchListTwo(Map searchMap) {
		
		Query query=new SimpleQuery();
		//添加查询条件
		org.springframework.data.solr.core.query.Criteria criteria=
				new org.springframework.data.solr.core.query.Criteria("course_keywords").is(searchMap.get("keywords"));
		query.addCriteria(criteria);
		
		query.setOffset(0);
		query.setRows(1000);
		
		ScoredPage<CeCourse> page = solrTemplate.queryForPage(query, CeCourse.class);
		
		for(CeCourse ce : page.getContent() ) {
			ce.setName(ce.getName().replace((String) searchMap.get("keywords"), "<em style='color:red'>"+searchMap.get("keywords")+"</em>"));
			ce.setDescription(ce.getDescription().replace((String) searchMap.get("keywords"), "<em style='color:red'>"+searchMap.get("keywords")+"</em>"));
			ce.setCategory(ce.getCategory().replace((String) searchMap.get("keywords"), "<em style='color:red'>"+searchMap.get("keywords")+"</em>"));

		}
		
		Map<String,Object> map=new HashMap<>();
		map.put("rows", page.getContent());
		return map;
	}

	
	private Map<String, Object> searchList(Map serchMap) {//这个高亮查询有问题，不知道怎么回事.此方法暂时停用 由searchListTwo代替
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		//1.高亮选项初始化
		HighlightQuery query = new SimpleHighlightQuery();
		
		//2.添加高亮域
		HighlightOptions highlightOptions = new HighlightOptions().addField("course_name");//.addField("course_description");
		highlightOptions.setSimplePrefix("<em style='color:red'>");//高亮前缀 
		highlightOptions.setSimplePostfix("</em>");//高亮后缀
		query.setHighlightOptions(highlightOptions);
		
		//3.按关键字查询
		org.springframework.data.solr.core.query.Criteria keywordsCriteria = 
				new org.springframework.data.solr.core.query.Criteria("course_keywords").is(serchMap.get("keywords"));
		query.addCriteria(keywordsCriteria);
		
		
		//**********  获取高亮结果集  *********
		//4.高亮对象  必须在这句代码之前筛选  
		HighlightPage<CeCourse> page = solrTemplate.queryForHighlightPage(query, CeCourse.class);
		
		//高亮入口集合（每条记录的的高度入口）
		List<HighlightEntry<CeCourse>> entryList = page.getHighlighted();
		
		System.out.println("entryList.size() -= "+entryList.size());
		
		for(HighlightEntry<CeCourse> entry: entryList) {
			//获取高亮列表(高亮域的个数)
			List<Highlight> highlightList = entry.getHighlights();
			System.out.println("highlightList.size() = "+highlightList.size());
			for(Highlight h: highlightList) {
				List<String> sns = h.getSnipplets();//每个域可能存储多值
				System.out.println("sns = "+sns);
			}
		}
		
		
		//5.获取高亮入口集合
//		for(HighlightEntry<CeCourse> h : page.getHighlighted()) {
//			
//			CeCourse ceCourse = h.getEntity();//获取原实体类
//			if(h.getHighlights().size()>0 && h.getHighlights().get(0).getSnipplets().size()>0){
//				//ceCourse.setDescription(h.getHighlights().get(0).getSnipplets().get(0));//设置高亮的结果
//				ceCourse.setName(h.getHighlights().get(0).getSnipplets().get(0));
//			}	
//			
//		}
		
//		for(CeCourse ce : page.getContent() ) {
//			System.out.println("naem = "+ce.getName());
//			ce.setName(ce.getName().replace((String) serchMap.get("keywords"), "<em style='color:red'>"+serchMap.get("keywords")+"</em>"));
//		}
	
		
		map.put("rows",page.getContent());
		
		return map;
		
	}
	
	
	/**
	 * 拿到课程id，根据视频id
	 */
	public Integer getCourseId(Integer vid) {
		
		//1.先根据视频id查找视频信息
		CeVideo ceVideo = videoMapper.selectByPrimaryKey(vid);
		
		//2.再根据视频信息中的章节id查找章节信息
		CeChapter ceChapter = chapterMapper.selectByPrimaryKey(ceVideo.getChapterId());
		
		return ceChapter.getSourceId();
	}
	
	

	

}
