package com.MVReservation001.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.MVReservation001.service.MovieService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		System.out.println("메인페이지 이동 요청");
		return "home";
	}
	
	@Autowired
	private MovieService mvsvc;
	
	@RequestMapping(value = "/addMovieList")
	public String addMovieList() throws IOException {
		System.out.println("/addMovieList 영화목록추가 요청");
		
		int insertResult = mvsvc.addMovieList();
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/addTheaterList")
	public String addTheaterList() throws IOException{
		System.out.println("극장목록 추가 요청");
		int insertResult = mvsvc.addTheaterList();
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/addScheduleList")
	public String addScheduleList() throws Exception{
		System.out.println("상영스케쥴 추가 요청");
		int insertResult = mvsvc.addScheduleList();
		
		return "redirect:/";
	} 
	

}















