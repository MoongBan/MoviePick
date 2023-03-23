package com.MVReservation001.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.MVReservation001.dao.MemberDao;
import com.MVReservation001.dto.MemberDto;
import com.MVReservation001.dto.ReservationDto;
import com.MVReservation001.dto.ReviewsDto;

@Service
public class MemberService {

	@Autowired
	private MemberDao mdao;
	
	@Autowired
	private ServletContext context;
	
	public int memberJoin(MemberDto member) throws IllegalStateException, IOException {
		System.out.println("MemberService memberJoin() 호출");
//		1. 파일명 확인
		MultipartFile mfile = member.getMfile();
		String mprofile = "";
		if(mfile.isEmpty()) { 	// mfile이 null이면 true 
			// 프로필 이미지 파일을 업로드 하지 않은 경우
			System.out.println("첨부파일 없음");
		} else {
			// 파일을 업로드 했을 경우
			System.out.println("첨부파일 있음");
			UUID uuid = UUID.randomUUID();
			mprofile = uuid.toString() + "_" + mfile.getOriginalFilename();
			// UUID : 총 32자리 임의의 코드를 만들어주는 역할
			
			String savePath = context.getRealPath("resources\\memberProfile");
			
			System.out.println(savePath);
			
//			2. 파일을 저장 
			//1. 파일을 저장할 경로 
			//2. 파일저장기능 호출
			File file = new File(savePath,mprofile);
			mfile.transferTo(file);
			
		}
		System.out.println("mprofile : " + mprofile);
		//첨부파일이 없을 경우 :: mprofile : 
		//첨부파일이 있을 경우 :: 32자리코드 + 첨부파일명
		member.setMprofile(mprofile);
		int joinResult = 0;
		try {
			joinResult = mdao.insertJoinMember(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(joinResult);
		return joinResult;
	}
	
	//로그인 기능
	public MemberDto memberLogin(String inputId, String inputPw) {
		System.out.println("MemberSerivce memberLogin() 호출");
		MemberDto loginId = mdao.selectLoginId(inputId,inputPw);
		return loginId;
	}

	public ArrayList<ReservationDto> getReInfo(String loginId) {
		System.out.println("MemberService getReInfo() 호출");
		ArrayList<ReservationDto> reInfoList = mdao.selectReInfo(loginId);
		return reInfoList;
	}

	public int writeReview(ReviewsDto review) {
		System.out.println("MemberService 리뷰작성 기능 호출");
		System.out.println(review);
		int insertResult = mdao.insertReview(review);
		return insertResult;
	}

	
	public ReviewsDto gerRvInfo(String recode) {
		System.out.println("MovieService 리뷰 조회 기능 호출");
		ReviewsDto rvInfo = mdao.selectRvInfo(recode);
		return rvInfo;
	}


}
