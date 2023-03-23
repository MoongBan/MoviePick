package com.MVReservation001.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.MVReservation001.dto.MemberDto;
import com.MVReservation001.service.adminService;

@Controller
public class AdminController {

	@Autowired
	private adminService adsvc;
	
	@GetMapping("/adminPage")
	public ModelAndView adminPage() {
		ModelAndView mav = new ModelAndView();
		System.out.println("관리자 페이지 이동");
		//1. 회원목록 조회
		ArrayList<MemberDto> MemberList = adsvc.getMemberList();
		mav.addObject("memList",MemberList);
		
		mav.setViewName("admin/adminMain");
		
		return mav;
	}
	
	@GetMapping("/JoinMemberList")
	public ModelAndView joinMemberList( ) {
		ModelAndView mav = new ModelAndView();
		System.out.println("회원목록 조회 기능");
		
		return mav;
	}
	
}
