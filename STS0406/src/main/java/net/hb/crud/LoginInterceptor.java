package net.hb.crud;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoginInterceptor extends HandlerInterceptorAdapter {

   private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
   
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    //jsp문서에서 세션session내장개체
		HttpSession session = request.getSession();
		String google = (String)session.getAttribute("daum");	
		System.out.println("LoginInterceptor인셉 세션=" + google);
		
		
		String uri = request.getRequestURI();
		 System.out.println("LoginInterceptor인셉  uri " + uri);    
	     //프로젝트 이름까지 찾아오기때문에 주소만  http://127.0.0.1:8080/crud/~~.do
	     int idx = uri.lastIndexOf("/");  //꿀팁
		 uri = uri.substring(idx+1);      //꿀팁 
		 System.out.println("LoginInterceptor인셉  uri " + uri); 
		
		boolean flag = false;
		if(google!=null){
			flag=true;	
		}else{
		  response.setContentType("text/html; charset=utf-8");
		  response.getWriter().append("<script>"
					+ "alert('LoginInterceptor인셉 로그인이필요합니다-tistis');"
		       		+ "location.href='login.do';"
				    + "</script>").flush();
		 flag=false;
		}
		return flag;
	}//end

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)		throws Exception {
		//컨트롤러의 메소드 끝나고 , 화면처리후 처리 
        logger.info("=======================afterCompletion start========================");
		logger.info("=======================afterCompletion end=========================");
	}//end
			
	
	 @Override
	 public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)		throws Exception {
		//컨트롤러의 메소드 끝나고 ,1처리 
	   logger.info("=======================postHandle start======================");
	   logger.info("=======================postHandle end=========================");	
	}//end

}//class LoginInterceptor END
