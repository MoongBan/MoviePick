package com.MVReservation001.dto;

import lombok.Data;

@Data
public class PageDto {
	private int reviewPage; // 현재 페이지 번호
	private int startPageNum; // 시작 페이지 번호
	private int endPageNum; // 끝 페이지 번호
	private int maxPageNum; // 최대 페이지 번호

}
