package com.MVReservation001.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MVReservation001.dao.adminDao;
import com.MVReservation001.dto.MemberDto;

@Service
public class adminService {

	@Autowired
	private adminDao adao;
	
	public ArrayList<MemberDto> getMemberList() {
		System.out.println("admin_Service 회원목록 조회 기능 호출");
		ArrayList<MemberDto> MemList = adao.selectMemberList();
		return MemList;
	}

}
