package com.onlinelearn.client.interceptor;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import entity.Result;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import utils.Const;
import utils.DateHelper;
import utils.TokenHelper;

public class TokenInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		response.setCharacterEncoding("utf-8");
		// Get authorization from Http request
		final String authHeader = request.getHeader(Const.JWT_HEADER);
		
		//print the authorization to console for debug
		System.out.println("hasUserAt"+DateHelper.getDateNow());
		System.out.println("auth = "+authHeader);
		
		 if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			 responseMessage(response, response.getWriter(), new Result(false, "JWT错误"));
			 System.out.println("new Result(false, (JWT错误)");
			 return false;
		 }
		 
		 // Then get the JWT token from authorization
         final String token = authHeader.substring(7);
         
         try {
             final Claims claims = Jwts.parser().setSigningKey(Const.JWT_SECRET_KEY).parseClaimsJws(token).getBody();
         } catch (Exception e) {
			 responseMessage(response, response.getWriter(), new Result(false, "JWT错误2"));
			 System.out.println("new Result(false, JWT错误2)");
			 return false;

		}
         
        TokenHelper tokenHelper = new TokenHelper(authHeader);
        if(tokenHelper.tokenExpIsFailed()) {
			 responseMessage(response, response.getWriter(), new Result(false, "JWT过期"));
			 System.out.println("new Result(false, JWT过期)");
			 return false;

        }
         
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	//请求不通过，返回错误信息给客户端
    private void responseMessage(HttpServletResponse response, PrintWriter out, Result res) {
        response.setContentType("application/json; charset=utf-8");  
        String json = JSONObject.toJSONString(res);
        out.print(json);
        out.flush();
        out.close();
    }
	
	

}
