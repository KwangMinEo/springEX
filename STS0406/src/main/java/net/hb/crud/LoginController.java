package net.hb.crud;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {
	
	@Autowired
	LoginDAO loginDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	

	@RequestMapping(value="login.do",method=RequestMethod.GET)
	 public String login(Model model){
		return "login";  //화면 login.jsp문서
	}//end
	
	@RequestMapping(value="loginprocess.do")
	public String loginprocess(LoginDTO dto, HttpServletResponse response, HttpSession session)	throws Exception
	{
		System.out.println("\n로그인컨트롤처리 넘어온userid=" + dto.getUserid() );
		System.out.println("로그인컨트롤처리 넘어온pwd=" + dto.getPwd());
		
		String result=loginDAO.login(dto);	
		System.out.println("로그인컨트롤처리 다오처리후=" + result);
		session.setAttribute("daum",result );
				
		if(result==null || result=="" || result.equals("") ){
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().append("<script>"
				   + "alert('LoginController 아이디,비번 일치하지않습니다 - youngrim');"
				   + "window.history.back();"
				   + "</script>").flush();
			return "redirect:/login.do";
		}
		//성공한 경우에는 시작페이지로 이동
		return "redirect:/boardList.do";
	}//end
	
	
	@RequestMapping(value="logout.do", method=RequestMethod.GET)
	public String logout(HttpSession session){
		session.invalidate(); 
		return "redirect:/index.jsp";
	}//end
	
}//LoginController class END