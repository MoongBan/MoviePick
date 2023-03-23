package com.MVReservation001.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.MVReservation001.dto.MovieDto;
import com.MVReservation001.dto.PageDto;
import com.MVReservation001.dto.ReservationDto;
import com.MVReservation001.dto.ReviewsDto;
import com.MVReservation001.dto.ScheduleDto;
import com.MVReservation001.dto.TheaterDto;
import com.MVReservation001.service.MemberService;
import com.MVReservation001.service.MovieService;
import com.google.gson.Gson;

@Controller
public class MovieController {

	@Autowired
	private MovieService mvsvc;
	
	@Autowired
	private MemberService msvc;
	
	
	@RequestMapping(value="/getTheaterScdate_ajax")
	public @ResponseBody String getTheaterScdate(String thcode, String scdate) {
		System.out.println("컨트롤러 getTheaterScdate_ajax 실행");
		System.out.println("thcode : " + thcode);
		System.out.println("scdate : " + scdate);
		String mvScInfoList = mvsvc.getMvtitleScdate_ajax(thcode,scdate);
		System.out.println(mvScInfoList);
		return new Gson().toJson(mvScInfoList);
	}
	
	
	@RequestMapping(value="/TheaterInfoPage")
	public ModelAndView TheaterInfoPage(String thcode) {
		System.out.println("극장 상세정보 페이지 이동 요청");
		ModelAndView mav = new ModelAndView();
		//1. 극장리스트 조회
		ArrayList<TheaterDto> thList = mvsvc.getTheaterList();
		mav.addObject("thList",thList);
		//2. 극장 상세정보 조회
		TheaterDto thInfo = mvsvc.getTheaterInfoList(thcode);
		mav.addObject("thInfo",thInfo);
		//3. 해당 극장의 상영스케쥴 조회
		ArrayList<ScheduleDto> scheduleList = mvsvc.getTheaterScheduleList(thInfo.getThcode());
		mav.addObject("dateList", scheduleList);
		
		mav.setViewName("movie/TheaterInfo");
		return mav;
	}
	
	@RequestMapping(value = "/theaterPage")
	public ModelAndView theaterPage() {
		System.out.println("극장 페이지 이동 요청");
		ModelAndView mav = new ModelAndView();
		ArrayList<TheaterDto> thList = mvsvc.getTheaterList();
		mav.addObject("thList",thList);
		mav.setViewName("movie/TheaterList");
		return mav;
	}
	
	
	@RequestMapping(value="/reCancelPage")
	public ModelAndView reCancelPage(String selectmovie, RedirectAttributes ra) {
		System.out.println("예매 취소 페이지 이동 요청");
		ModelAndView mav = new ModelAndView();
		// 전달받은 파라미터 출력
		System.out.println("취소할 영화코드 : " + selectmovie);
		// 로그인 아이디 
		String loginId = (String)session.getAttribute("loginId");
		if(loginId == null) {
			ra.addFlashAttribute("redirectMsg", "로그인 후 이용가능합니다.");
			mav.setViewName("redirect:/memberLoginForm");
		}
		//삭제기능
		int deResult = mvsvc.cancelRemv(selectmovie,loginId);
		if(deResult > 0) {
			System.out.println("삭제완료");
			ArrayList<ReservationDto> reserveList = msvc.getReInfo(loginId); 
			mav.addObject("reInfoList",reserveList);
			mav.setViewName("member/ReservationInfo");
		} else {
			System.out.println("삭제실패");
			mav.setViewName("member/ReservationInfo");
		}
		
		return mav;
	}
	
	
	@RequestMapping(value = "/ticketPage")
	public ModelAndView ticketPage() {
		System.out.println("예매 페이지 이동 요청");
		ModelAndView mav = new ModelAndView();
		
		//영화목록조회
		ArrayList<MovieDto> mvList = mvsvc.getMovieList();
		mav.addObject("mvList", mvList);
		
		//극장목록조회
		ArrayList<TheaterDto> thList = mvsvc.getTheaterList();
		mav.addObject("thList", thList);
		
		mav.setViewName("movie/Ticket");
		return mav;
	}
	
	@RequestMapping(value = "/getAllMovieList")
	public @ResponseBody String getAllMovieList() {
		ArrayList<MovieDto> allMvList = mvsvc.getMovieList();
		return new Gson().toJson(allMvList);
	}

	@RequestMapping(value = "/getAllTheaterList")
	public @ResponseBody String getAllTheaterList() {
		ArrayList<TheaterDto> allThList = mvsvc.getTheaterList();
		return new Gson().toJson(allThList);
	}	
	
	@RequestMapping(value = "/getReTheaterList")
	public @ResponseBody String getReTheaterList(String mvcode) {
		System.out.println("ajax_예매가능한 극장 목록 조회 요청");
		String thList = mvsvc.getReTheaterList(mvcode);
		return thList;
	}
	
	@RequestMapping(value = "/getReMovieList")
	public @ResponseBody String getReMovieList(String thcode) {
		System.out.println("ajax_예매가능한 영화 목록 조회 요청");
		String mvList = mvsvc.getReMovieList(thcode);
		return mvList;
	}	
	
	@RequestMapping(value = "/getScheduleDateList")
	public @ResponseBody String getScheduleDateList(String scmvcode,String scthcode) {
		System.out.println("ajax_예매가능한 날짜 목록 조회 요청");
		System.out.println("선택한 영화코드 : " + scmvcode);
		System.out.println("선택한 극장코드 : " + scthcode);
		String dateList = mvsvc.getScheduleDateList(scmvcode, scthcode);
		return dateList;
	}
	
	@RequestMapping(value = "/getScheduleRoomTimeList")
	public @ResponseBody String getScheduleRoomTimeList(String scmvcode,String scthcode, String scdate) {
		System.out.println("ajax_예매가능한 상영관 및 시간 목록 조회 요청");
		System.out.println("선택한 영화코드 : " + scmvcode);
		System.out.println("선택한 극장코드 : " + scthcode);
		System.out.println("선택한 날짜 : " + scdate);
		String roomTimeList = mvsvc.getScheduleRoomTimeList(scmvcode, scthcode, scdate);
		return roomTimeList;		
	}
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/reserveMovie_PayReady")
	public @ResponseBody String reserveMovie_PayReady(ReservationDto reInfo) throws Exception {
		System.out.println("ajax_영화예매_결제준비 요청");
		//결제 QR코드 페이지 URL
		String nextUrl = mvsvc.reserveMovie_PayReady(reInfo,session);
		
		return nextUrl;
	}

	@RequestMapping(value = "/reserveMovie_PayApproval")
	public ModelAndView reserveMovie_PayApproval(String pg_token, String recode) throws Exception {
		System.out.println("ajax_영화예매_결제승인 요청");
		ModelAndView mav = new ModelAndView();
		
		System.out.println("pg_token : " + pg_token);
		String tid = (String)session.getAttribute("payTid");
		System.out.println("tid : " + tid);
		
		String reserveResult = mvsvc.reserveMovie_PayApproval(tid, pg_token, recode);
		
		mav.addObject("reserveResult", reserveResult); // Approval, Fail
		mav.setViewName("movie/Ticket_PayResult");
		return mav;
	}	
	
	
	@RequestMapping(value = "/reserveMovie_PayCancel")
	public ModelAndView reserveMovie_PayCancel(String recode) throws Exception {
		System.out.println("ajax_영화예매_결제취소");
		ModelAndView mav = new ModelAndView();
		int deleteReserve = mvsvc.deleteReserveInfo(recode);
		mav.addObject("reserveResult", "Cancel");
		mav.setViewName("movie/Ticket_PayResult");
		return mav;
	}		
	
	@RequestMapping(value = "/reserveMovie_PayFail")
	public ModelAndView reserveMovie_PayFail(String recode) throws Exception {
		System.out.println("ajax_영화예매_결제실패");
		ModelAndView mav = new ModelAndView();
		int deleteReserve = mvsvc.deleteReserveInfo(recode);
		mav.addObject("reserveResult", "Fail");
		mav.setViewName("movie/Ticket_PayResult");
		return mav;
	}	
	
	
	@RequestMapping(value = "/reserveMovie")
	public @ResponseBody String reserveMovie(ReservationDto reserveInfo)  {
		System.out.println("ajax_영화예매 처리 요청");
		System.out.println(reserveInfo);
		//서비스 예매처리 기능 호출
		String reserveResult = mvsvc.reserveMovie(reserveInfo);
		
		return reserveResult;
	}
	

	
	@RequestMapping(value="/moviePage")
	public ModelAndView moviePage() {
		System.out.println("영화 목록 페이지 이동 요청");
		ModelAndView mav = new ModelAndView();
		ArrayList<MovieDto> MvList = mvsvc.getMovieList();
		
		mav.addObject("WholeMvList", MvList);
		mav.setViewName("movie/movieList");
		return mav;
	}
	
	
	@RequestMapping(value="/movieInfoPage")
	public ModelAndView movieInfoPage(String selectmovie,@RequestParam(value="reviewPage", defaultValue = "1") int reviewPage) {
		// @RequestParam(value="reviewPage", defaultValue = "1") int reviewPage :: 
		// "reviewPage"라는 파라미터를 reviewPage라는 이름으로 사용하고, 파라미터의 기본값은 1로 지정
		System.out.println("영화 상세페이지 이동 요청");
		System.out.println("영화코드 : " + selectmovie);
		System.out.println("관람평 페이지 번호 : " + reviewPage);
		int reviewPageLimit = 2; // 한 페이지에 보여줄 관람평의 개수
		int reviewPageNumber = 5; // 페이지 번호의 개수 (한 페이지에 보여줄 '페이지번호'의 개수)
		
		ModelAndView mav = new ModelAndView();
		// 영화정보 조회
		MovieDto mvInfo  = mvsvc.getMvInfo(selectmovie);
		mav.addObject("mvInfo",mvInfo);
		
		// 관람평 조회를 위해 관람평 조회 메서드 호출
		String loginId = (String)session.getAttribute("loginId");
		ArrayList< Map<String, String> > rvInfoList = mvsvc.getRvInfo(loginId,selectmovie,reviewPage,reviewPageLimit,reviewPageNumber); 
		mav.addObject("rvInfoList",rvInfoList);
		
		//관람평 페이지 정보 조회
		PageDto pageInfo = mvsvc.gerReviewPageInfo(selectmovie,reviewPage,reviewPageLimit,reviewPageNumber);
		mav.addObject("pageInfo",pageInfo);
		mav.setViewName("movie/movieInfo");
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
