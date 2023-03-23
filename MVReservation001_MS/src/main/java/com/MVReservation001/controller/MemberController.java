package com.MVReservation001.controller;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.MVReservation001.dto.MemberDto;
import com.MVReservation001.dto.ReservationDto;
import com.MVReservation001.dto.ReviewsDto;
import com.MVReservation001.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService msvc;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletResponse response;
	

	
	@RequestMapping(value="/reviewWrite")
	public ModelAndView reviewWrite(ReviewsDto reviews,String rvrecode,String mvtitle,RedirectAttributes ra) {
		System.out.println("리뷰작성 기능 호출");
		ModelAndView mav = new ModelAndView();
		reviews.setRvrecode(rvrecode);
		String loginId = (String)session.getAttribute("loginId");
		reviews.setRvmid(loginId);
		int writeResult = msvc.writeReview(reviews);
		if(writeResult > 0) {
			ra.addFlashAttribute("redirectMsg", "리뷰가 등록되었습니다.");
			// 리뷰가 등록되었으므로 리뷰 재작성 방지를 위해 review 정보로 리뷰가 있나 select하기
			mav.setViewName("member/ReservationInfo");
		} else {
			System.out.println("리뷰등록완료실패");
			ra.addFlashAttribute("redirectMsg", "리뷰 등록에 실패하였습니다.");
			mav.setViewName("member/ReservationInfo");
		}
		return mav;
	}
	
	@RequestMapping(value="/writeReviewForm")
	public ModelAndView reviewForm(String recode ,String mvtitle) {
		System.out.println("리뷰작성페이지 이동 기능 호출");
		ModelAndView mav = new ModelAndView();
		mav.addObject("recode",recode);
		mav.addObject("mvtitle",mvtitle);
		mav.setViewName("member/ReviewWriteForm");
		return mav;
	}
	
	@RequestMapping(value="/openReviewForm")
	public ModelAndView openReviewForm(String recode,String mvtitle) {
		System.out.println("리뷰 조회 페이지 이동 기능 호출");
		ModelAndView mav = new ModelAndView();
		// recode는 rvrecode와 같기때문에 recode로 select해도 된다. 
		ReviewsDto rvInfo = msvc.gerRvInfo(recode);
		System.out.println("mvtitle"+mvtitle);
		mav.addObject("rvInfo",rvInfo);
		mav.addObject("mvtitle",mvtitle);
		mav.setViewName("member/ReviewInfoForm");
		return mav;
	}
	
	
	
	@RequestMapping(value="/reservationInfo")
	public ModelAndView reservationInfo() {
		ModelAndView mav = new ModelAndView();
		System.out.println("예매정보 페이지 이동요청");
		
		String loginId = (String)session.getAttribute("loginId");
		System.out.println("로그인아이디 : " + loginId);
		//1. 예매정보 조회
		/* ArrayList< Map<String,String> > reInfoList = msvc.getReInfo(loginId); */
		 ArrayList<ReservationDto> reInfoList = msvc.getReInfo(loginId); 
		//Map<K, V> map01 = new Map<K, V>(); 
		// K:key, V:value :: json오브젝트와 같음. 맵 객체.get으로 key값을 지정해서 데이터를 찾을 수도 있음
		
		mav.addObject("reInfoList",reInfoList);
		
		mav.setViewName("member/ReservationInfo");
		return mav;
	}
	
	
	
	
	
	
	@RequestMapping(value = "/memberJoinForm")
	public ModelAndView memberJoinForm() {
		System.out.println("회원가입 페이지 이동요청");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/MemberJoinForm");
		return mav;
	}
	@RequestMapping(value="/memberJoin")
	public ModelAndView memberJoin(MemberDto member) throws IllegalStateException, IOException {
		System.out.println("회원가입 요청");
		System.out.println(member);
		ModelAndView mav = new ModelAndView();
		int insertResult = msvc.memberJoin(member);
		if(insertResult > 0) {
			System.out.println("회원가입 성공");
			mav.setViewName("redirect:/");
		} else {
			System.out.println("회원가입 실패");
			mav.setViewName("redirect:/memberJoinForm");
		}
		return mav;
	}
	
	
	
	@RequestMapping(value = "/memberLoginForm")
	public ModelAndView memberLoginForm() {
		System.out.println("로그인 페이지 이동요청");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/MemberLoginForm");
		return mav;
	}
	
	@RequestMapping(value="/memberLogin")
	public ModelAndView memberLogin(MemberDto member, RedirectAttributes ra) throws IOException {
		ModelAndView mav = new ModelAndView();
		System.out.println("로그인 기능 요청");
		String inputId = member.getMid();
		String inputPw = member.getMpw();
		System.out.println("입력아이디 : " +inputId);
		System.out.println("입력비번 : " + inputPw);
		MemberDto loginInfo = msvc.memberLogin(inputId,inputPw); 
		if(loginInfo != null) {
			if(loginInfo.getMid().equals("admin")) {
				System.out.println("관리자 계정으로 로그인");
//				 ra.addFlashAttribute("msg","관리자 계정으로 로그인"); 
				mav.setViewName("admin/adminMain");
				return mav;
			}
			// 세션에 로그인 정보 저장(MemberDto >> [mid, mprofile]
//			session.setAttribute("loginInfo", loginInfo);
			System.out.println("로그인 된 아이디 : " + loginInfo.getMid());
			session.setAttribute("loginId",loginInfo.getMid());
			session.setAttribute("loginProfile", loginInfo.getMprofile());
			System.out.println("로그인 성공");
			mav.setViewName("redirect:/");
		} else {
			System.out.println("로그인 실패");
			mav.setViewName("member/MemberLoginForm");
		}
		return mav;
	}
	
	@RequestMapping(value="/memberLogout")
	public ModelAndView memberLogout() throws IOException {
		ModelAndView mav = new ModelAndView();
		System.out.println("로그아웃 요청");
		session.invalidate();
//		session.removeAttribute("loginId"); 를 써도 됨
		mav.setViewName("home");
		return mav;
		
	}
	
}
