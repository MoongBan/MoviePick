package com.MVReservation001.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.MVReservation001.dto.MemberDto;
import com.MVReservation001.dto.ReservationDto;
import com.MVReservation001.dto.ReviewsDto;

public interface MemberDao {
	
	@Select("SELECT MCODE FROM MEMBERS WHERE MID=#{loginId}")
	String selectMemberCode(String loginId);
	
	
	@Insert("INSERT INTO MEMBERS(MID, MPW, MNAME, MBIRTH, MADDR, MEMAIL, MPROFILE, MSTATE) "
			   + "VALUES(#{mid}, #{mpw}, #{mname}, TO_DATE(#{mbirth},'YYYY-MM-DD'), "
			   + " #{maddr}, #{memail}, #{mprofile}, '0' )")
	int insertJoinMember(MemberDto member);

	
	@Select("SELECT MID,MPROFILE FROM MEMBERS WHERE MID = #{mid} " )
	MemberDto selectLoginId(@Param("mid")String inputId, @Param("mpw")String inputPw);

	@Select("SELECT RV.RVRECODE, RV.RVCOMMENT, MV.MVPOS, RE.RECODE, MV.MVTITLE, MV.MVCODE ,RE.REMID, TH.THNAME, RE.REROOM ,RE.REDATE ,RE.RENUMBER "
			+ "FROM RESERVATION RE "
			+ "INNER JOIN MOVIES MV "
			+ "ON RE.REMVCODE = MV.MVCODE "
			+ "INNER JOIN THEATERS TH "
			+ "ON RE.RETHCODE = TH.THCODE "
			+ "LEFT OUTER JOIN REVIEWS RV "
			+ "ON RE.RECODE = RV.RVRECODE "
			+ "WHERE RE.REMID = #{loginId} ")
	ArrayList<ReservationDto> selectReInfo(String loginId);

	@Select("SELECT MAX(RVRECODE) FROM REVIEWS")
	String selectMaxRvcode();

	
	@Insert("INSERT INTO REVIEWS(RVRECODE, RVMID, RVCOMMENT, RVRECOMMEND, RVDATE) "
			+ "VALUES(#{rvrecode}, #{rvmid}, #{rvcomment}, '1', SYSDATE) ")
	int insertReview(ReviewsDto review);


	@Select("SELECT RVCOMMENT, RVRECOMMEND, TO_CHAR(RVDATE,'RR-MM-DD HH24:MI') AS RVDATE FROM REVIEWS WHERE RVRECODE = #{recode} ")
	ReviewsDto selectRvInfo(String recode);


}
