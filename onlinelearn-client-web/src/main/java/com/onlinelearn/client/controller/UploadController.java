package com.onlinelearn.client.controller;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import entity.Result;
import utils.FastDFSClient;
/**
 * 文件上传Controller
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/file")
public class UploadController {
	
	@Value("${FILE_SERVER_URL}")
	private String FILE_SERVER_URL;//文件服务器地址

	@RequestMapping("/uploadImg")
	public Result upload(@RequestParam("file")MultipartFile file){	

		if(file == null) {
			return new Result(false, "上传失败!!上传文件为空");
		}
		
		//1、取文件的扩展名
		String originalFilename = file.getOriginalFilename();
		String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		try {
			//2、创建一个 FastDFS 的客户端
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
			//3、执行上传处理
			String path = fastDFSClient.uploadFile(file.getBytes(), extName);
			//4、拼接返回的 url 和 ip 地址，拼装成完整的 url
			String url = FILE_SERVER_URL + path;			
			return new Result(true,url);			
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "上传失败");
		}		
	}
	
	@RequestMapping("/uploadU")
	public String uploadU(@RequestParam("file")MultipartFile file){	

		//1、取文件的扩展名
		String originalFilename = file.getOriginalFilename();
		String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		try {
			//2、创建一个 FastDFS 的客户端
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
			//3、执行上传处理
			String path = fastDFSClient.uploadFile(file.getBytes(), extName);
			//4、拼接返回的 url 和 ip 地址，拼装成完整的 url
			String url = FILE_SERVER_URL + path;	
			System.out.println("url = "+path);
			return path;			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}		
	}
	
	@RequestMapping("/uploadImgCK")
	public String ckeditorUpload(@RequestParam("upload") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		
		
		System.out.println("recive file2!");
	
		
		System.out.println("file.length = "+file.getSize());
		System.out.println("file ContentType = "+file.getContentType());
		
		//1.取文件的扩展名
		String originalFilename = file.getOriginalFilename();
		String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		
		try {
			//2、创建一个 FastDFS 的客户端
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
			//3、执行上传处理
			String path = fastDFSClient.uploadFile(file.getBytes(), extName);
			//4、拼接返回的 url 和 ip 地址，拼装成完整的 url
			String url = FILE_SERVER_URL + path;
			
			System.out.println("imgURL = "+url);
			
			// 将上传的图片的url返回给ckeditor  
			String callback = request.getParameter("CKEditorFuncNum");  
			String reString = "<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(2, '"+url+"');</script>";
			return reString;
		
		} catch (Exception e) {
			System.out.println("上传失败！！！！！！！");
			return "失败";
		}
		
	}
	
	
}
