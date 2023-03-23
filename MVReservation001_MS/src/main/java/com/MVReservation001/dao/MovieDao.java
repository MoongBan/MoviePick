package com.MVReservation001.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.checkerframework.checker.units.qual.degrees;
import org.checkerframework.checker.units.qual.s;

import com.MVReservation001.dto.MovieDto;
import com.MVReservation001.dto.ReservationDto;
import com.MVReservation001.dto.ReviewsDto;
import com.MVReservation001.dto.ScheduleDto;
import com.MVReservation001.dto.TheaterDto;

public interface MovieDao {
	@Select("SELECT MVTITLE "
			  + "FROM MOVIES "
			  + "WHERE MVTITLE = #{mvtitle}")
	String selectCheckMovie(String mvtitle);

	@Select("SELECT MAX(MVCODE) "
			  + "FROM MOVIES")
	String selectMaxMvcode();

	@Insert("INSERT INTO MOVIES(MVCODE, MVTITLE, MVDIR, MVACT, MVGENRE, MVINFO, MVDATE, MVPOS) "
			  + "VALUES(#{mvcode}, #{mvtitle}, #{mvdir}, #{mvact}, #{mvgenre}, #{mvinfo}, TO_DATE(#{mvdate},'YYYY.MM.DD'), #{mvpos} ) ")
	int insertMovie(MovieDto movie);


	

	
	@Select("SELECT MAINTH.*, SUBTH.SCTHCODE AS CHECKTH "
		  + "FROM THEATERS MAINTH "
		  + "LEFT OUTER JOIN ( SELECT SCTHCODE "
		                    + "FROM SCHEDULES "
		                    + "WHERE SCMVCODE = #{scmvcode} AND SCDATE > SYSDATE "
		                    + "GROUP BY SCTHCODE) SUBTH "
		  + "ON MAINTH.THCODE = SUBTH.SCTHCODE "
		  + "ORDER BY CHECKTH ")
	ArrayList<TheaterDto> selectReTheaterList(String scmvcode);
	
	@Select("SELECT MAINMV.*, SUBMV.SCMVCODE AS CHECKMV "
		  + "FROM MOVIES MAINMV "
		  + "LEFT OUTER JOIN ( SELECT SCMVCODE "
			                + "FROM SCHEDULES "
			                + "WHERE SCTHCODE = #{scthcode} AND SCDATE > SYSDATE "
			                + "GROUP BY SCMVCODE) SUBMV "
		  + "ON MAINMV.MVCODE = SUBMV.SCMVCODE "
		  + "ORDER BY CHECKMV ")	
	ArrayList<MovieDto> selectReMovieList(String scthcode);	
	
	@Insert("INSERT INTO THEATERS(THCODE, THNAME, THADDR, THTEL) "
		  + "VALUES(#{thcode}, #{thname},#{thaddr},#{thtel} ) ")
	int insertTheater(TheaterDto theater);
	
	@Select("SELECT THCODE FROM THEATERS WHERE THCODE = #{cgvThCode}")
	String selectCheckTheater(String cgvThCode);
	
	@Select("SELECT MVCODE FROM MOVIES WHERE MVTITLE = #{mvtitle}")
	String selectMvcode(String mvtitle);

	@Insert("INSERT INTO SCHEDULES(SCMVCODE, SCTHCODE, SCROOM, SCDATE) "
		  + "VALUES(#{scmvcode}, #{scthcode}, #{scroom}, TO_DATE(#{scdate},'YYYYMMDD HH24:MI')  ) ")
	int insertSchedule(ScheduleDto schedule);


	@Select("SELECT TO_CHAR(SCDATE,'YYYY-MM-DD') AS SCDATE "
		  + "FROM SCHEDULES "
		  + "WHERE SCMVCODE = #{scmvcode} AND SCTHCODE = #{scthcode} "
		  + "AND SCDATE > SYSDATE "
		  + "GROUP BY TO_CHAR(SCDATE,'YYYY-MM-DD') "
		  + "ORDER BY SCDATE")
	ArrayList<ScheduleDto> selectReScheDuleDateList(@Param("scmvcode") String scmvcode, @Param("scthcode") String scthcode);
	
	@Select("SELECT SCROOM, TO_CHAR(SCDATE,'HH24:MI') AS SCDATE "
		  + "FROM SCHEDULES "
		  + "WHERE SCMVCODE = #{scmvcode} AND SCTHCODE = #{scthcode} "
		  + "AND TO_CHAR(SCDATE,'YYYY-MM-DD') = #{scdate}")
	ArrayList<ScheduleDto> selectReScheDuleRoomTimeList(@Param("scmvcode") String scmvcode, @Param("scthcode") String scthcode,@Param("scdate") String scdate);

	@Select("SELECT * FROM MOVIES WHERE MVCODE = #{remvcode}")
	MovieDto selectMovieInfo(String remvcode);

	
	@Select("SELECT MAX(RECODE) FROM RESERVATION")	
	String selectMaxRecode();
	
	@Insert("INSERT INTO RESERVATION(RECODE, REMID, RETHCODE, REROOM, REDATE, REMVCODE, RENUMBER) "
		  + "VALUES(#{recode}, #{remid}, #{rethcode}, #{reroom}, TO_DATE(#{redate},'YYYY-MM-DD HH24:MI'), #{remvcode}, #{renumber} )")
	int insertReservation(ReservationDto reserveInfo);

	@Delete("DELETE FROM RESERVATION WHERE RECODE = #{recode}")
	int deleteReservation(String recode);

	@Select("SELECT COUNT(*) FROM RESERVATION WHERE REMID = #{loginId}")
	int selectMemberReCount(String loginId);

	@Insert("INSERT INTO PAYINFO(RECODE, TID, MID, CANCEL_AMOUNT) "
		  + "VALUES(#{recode}, #{tid}, #{mid}, #{total} )")
	int insertPayInfo(@Param("recode") String recode, @Param("tid")String tid, @Param("mid")String loginId, @Param("total")String total);



	
	@Select("SELECT * FROM MOVIES ORDER BY MVCODE")
	ArrayList<MovieDto> selectMovieList();
	

	ArrayList<MovieDto> selectMovieList_Mapper();

	int selectTotalRenumber();

	@Select("SELECT MVCODE, MVTITLE, MVDIR, MVGENRE, MVINFO, MVACT, TO_CHAR(MVDATE,'YYYY-MM-DD') AS MVDATE, MVPOS FROM MOVIES WHERE MVCODE = #{selectmovie}")
	MovieDto selectMvInfo(String selectmovie);

	@Delete("DELETE FROM RESERVATION WHERE REMVCODE = #{remvcode} AND REMID = #{loginId}") 
	int deleteReMovie(@Param("remvcode")String selectmovie, @Param("loginId")String loginId);

	//@Select("SELECT * "
		//	+ "FROM REVIEWS RV, RESERVATION RE "
			//+ "WHERE RV.RVRECODE = RE.RECODE AND RVMID = #{loginId} AND REMVCODE = #{mvcode}")
	ArrayList<Map<String, String>> selectRvInfoList(@Param("loginId")String loginId, @Param("mvcode")String selectmovie, @Param("startRow")int startRow,@Param("endRow") int endRow);

	int selectReviewCount(String selectmovie);
	
	ArrayList<Map<String, String>> selectThScInfoList(String selectTheater);
	
	@Select("SELECT * FROM THEATERS")
	ArrayList<TheaterDto> selectTheaterList();


	@Select("SELECT * FROM THEATERS WHERE THCODE = #{thcode}")
	TheaterDto selectTheaterInfo(String thcode);



	ArrayList<ScheduleDto> selectTheaterSchedulesList(String thcode);

	
	@Select("SELECT MV.MVCODE, MV.MVTITLE, MV.MVINFO, MV.MVGENRE, TO_CHAR(MV.MVDATE, 'YYYY-MM-DD') AS MVDATE,  "
			+ " SC.SCROOM, TO_CHAR(SC.SCDATE,'HH24:MI') AS SCTIME, TO_CHAR(SC.SCDATE,'YY-MM-DD') AS SCDATE, SC.SCTHCODE "
			+ "FROM SCHEDULES SC "
			+ "INNER JOIN MOVIES MV "
			+ "ON SC.SCMVCODE = MV.MVCODE"
			+ "WHERE SC.SCTHCODE = #{scthcode } AND TO_CHAR(SC.SCDATE,'YYYY-MM-DD') = #{scdate}  AND SC.SCDATE > SYSDATE"
			+ "ORDER BY MV.MVCODE, SC.SCROOM, TO_CHAR(SC.SCDATE,'HH24:MI') ")
	ArrayList<Map<String, String>> selectMvScheduleInfoList(@Param("thcode")String thcode, @Param("scdate")String scdate);

	
	
}
