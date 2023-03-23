package com.MVReservation001.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Select;

import com.MVReservation001.dto.MemberDto;

public interface adminDao {

	@Select("SELECT * FROM MEMBERS")
	ArrayList<MemberDto> selectMemberList();

}
