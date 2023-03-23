package com.MVReservation001.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DTest {

	public static void main(String[] args) throws Exception {
		
		Date date = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("MMddHHmmss");
		System.out.println( simple.format(date));
		String a = "T0001-1223-M0001-00001";
		System.out.println(a.length());
	}

}
