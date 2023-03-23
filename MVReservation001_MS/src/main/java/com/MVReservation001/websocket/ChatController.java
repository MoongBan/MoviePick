package com.MVReservation001.websocket;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {
	
	@Autowired
	private HttpSession session;
	
	
	@RequestMapping(value="/noticeSendPage")
	public String noticeSendPage() {
		return "chat/noticeSendPage";
	}
	
	
	
	@RequestMapping(value="/chatPage")
	public ModelAndView chatPage() {
		ModelAndView mav = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		if(loginId == null) {
			mav.setViewName("redirect:/");
		} else {
			System.out.println("채팅페이지 이동 요청");
			mav.setViewName("chat/chatPage");
		}
		return mav;
	}
	
	@RequestMapping(value="/chatUserPage")
	public ModelAndView chatUserPage() {
		ModelAndView mav = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		if(loginId == null) {
			mav.setViewName("redirect:/");
		} else {
			System.out.println("채팅유저페이지 이동 요청");
			mav.setViewName("chat/chatUserPage");
		}
		return mav;
	}
	
}
