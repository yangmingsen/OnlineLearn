package com.onlinelearn.client.filter;

import java.io.IOException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Const;
 
/**
 * 头部过滤器
 * @author 
 */
public class HeaderFilter implements Filter{
 
	public void destroy() {
		
	}
 
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse) res;
		
//		String originHeader = request.getHeader("Origin");
//		response.setHeader("Access-Control-Allow-Origin", originHeader);
//		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
//		response.setHeader("Access-Control-Max-Age", "5242880"); 
//		response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");  
//		response.setHeader("Access-Control-Allow-Credentials", "true");  
//		response.setHeader("XDomainRequestAllowed","1");   
//		response.setHeader("XDomainRequestAllowed","1");
		
//		response.setHeader("Access-Control-Allow-Origin", "*");
		
		response.setHeader("Access-Control-Max-Age", "5242880");  
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token,Authorization,file");//请求头必须和前端对应, 因为Authorization没写导致一直无法接收token
        response.setHeader("Access-Control-Expose-Headers", "*");
      
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, res);
 
        }
        
        chain.doFilter(request, response);  
	}
 
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}

