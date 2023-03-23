package com.MVReservation001.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckHandler extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		// 요청받을 시 컨트롤러와 연결을 해주기 전에 처리를 해주는 부분
		String loginId = (String)request.getSession().getAttribute("loginId"); //요청의 session에서 loginId인 attribute를 받아옴
		if(loginId == null) {
			System.out.println("로그인 되어 있지 않음");
			response.getWriter().println("<script>");
			response.getWriter().println("alert('로그인 후에 이용 가능합니다.')");
			response.getWriter().println("location.href='memberLoginForm'");
			response.getWriter().println("</script>");
//			response.sendRedirect("memberLoginForm");
			
			return false;
		} 
		
		return true;
	}
	
	
	
}
